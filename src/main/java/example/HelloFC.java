package example;

import DTO.AnnexFunctionDTO;
import Enum.OssPicTransEnum;
import com.aliyun.fc.runtime.*;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import entity.SimpleRequestEntity;
import entity.SimpleResponseEntity;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import util.*;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Description:阿里云函数计算处理类,用于文件加水印打包下载
 * @author: shuangxi
 * @create: 2018/12/13下午6:55
 */
public class HelloFC implements FunctionInitializer,PojoRequestHandler<SimpleRequestEntity, SimpleResponseEntity> {
    //阿里云水印内容标志
    public static final String OSS_WATER_MARK_SUFFIX = "text_";
    //阿里云图片处理标志
    public static final String OSS_FILE_HANDLE_SUFFIX = "x-oss-process";
    //文件下载根路径
    public static final String PARENT_DIR = "/tmp/download/";
    private static ExecutorService executor = new ThreadPoolExecutor(5, 5, 200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new MyThreadFactory("HelloFC 异步下载线程池"));
    /**
     * 函数主入口,需要配置在阿里云的函数入口,内容如下: example.HelloFC::handleRequest
     * @param request
     * @param context
     * @return
     */
    @Override
    public SimpleResponseEntity handleRequest(SimpleRequestEntity request, Context context) {
        try {
            //创建阿里云OSS链接
            String endpoint = request.getEndpoint();
            String bucketName = request.getBucket();
            Credentials creds = context.getExecutionCredentials();
            OSSClient client = new OSSClient(
                    endpoint, creds.getAccessKeyId(), creds.getAccessKeySecret(), creds.getSecurityToken());
            //获取参数
            List<AnnexFunctionDTO> annexFunctionDTOList = request.getKeyList();
            if (CollectionUtils.isEmpty(annexFunctionDTOList)) {
                return null;
            }
            //水印内容转换
            String now = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            //文件下载路径,打包路径
            String dir = PARENT_DIR + "/" + now + "/";
            String zipName = now + ".zip";
            long t1=System.currentTimeMillis();
            CountDownLatch cdl = new CountDownLatch(annexFunctionDTOList.size());
            //循环获取url
            for (AnnexFunctionDTO annexFunctionDTO : annexFunctionDTOList) {
                executor.execute(()->{
                    try {
                        String xOssProcess = null;
                        if (StringUtils.isNotBlank(request.getWatermark())) {
                            String watermark = null;
                            watermark = UrlBase64Util.encryptBASE64(request.getWatermark());
                            xOssProcess = OssPicTransEnum.IMAGE_REAL.getName().replaceAll(OSS_WATER_MARK_SUFFIX, OSS_WATER_MARK_SUFFIX+watermark);
                        }
                        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, annexFunctionDTO.getKey());
                        //图片自动加水印
                        if (ImageUtil.isPic(annexFunctionDTO.getKey()) && StringUtils.isNotBlank(xOssProcess)) {
                            Map<String, String> map = new HashMap();
                            map.put(OSS_FILE_HANDLE_SUFFIX, xOssProcess);
                            generatePresignedUrlRequest.setQueryParameter(map);
                        }
                        //oss文件加密
                        long expireEndTime = System.currentTimeMillis() + 60 * 60 * 1000;
                        Date expiration = new Date(expireEndTime);
                        generatePresignedUrlRequest.setExpiration(expiration);
                        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
                        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
                        //下载文件
                        String targetDir = dir + annexFunctionDTO.getFileDir();
                        File file = new File(targetDir);
                        file.mkdirs();
                        String fileName = annexFunctionDTO.getFileName();
                        DownloadnUtil.fetchContent(url.toString(), targetDir + fileName);
                    } catch (Exception e) {
                    }finally {
                        cdl.countDown();
                    }
                });
            }
            try {
                cdl.await();
            } catch (InterruptedException e) {
                throw new InterruptedException();
            }
            long t2=System.currentTimeMillis();
            //打包文件
            CompressedFileUtil compressedFileUtil = new CompressedFileUtil();
            String targetName = null;
            //将dir下的文件打包至PARENT_DIR路径下
            targetName = compressedFileUtil.compressedFile(dir, PARENT_DIR);
            File file = new File(PARENT_DIR + targetName);
            long t3=System.currentTimeMillis();
            //打包后上传文件
            String key = request.getGroupName()+"/"+targetName;
            client.putObject(bucketName, key, file);
            long t4=System.currentTimeMillis();
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, key);
            long expireEndTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
            Date expiration = new Date(expireEndTime);
            generatePresignedUrlRequest.setExpiration(expiration);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            URL zipUrl = client.generatePresignedUrl(generatePresignedUrlRequest);
            client.shutdown();
            String msg = "文件下载总时长:"+(t2-t1)+" 文件打包总时长:"+(t3-t2)+" 文件上传总时长"+(t4-t3);
            return new SimpleResponseEntity(key,zipUrl.toString(),msg);
            //返回文件信息
        }catch (Exception e){
            JSONObject jsonObject = JSONObject.fromObject(request);
            return new SimpleResponseEntity(null,null,"jsonObject:"+jsonObject.toString()+",error:"+e.getMessage());
        }finally {
            executor.shutdown();
        }
    }

    /**
     * content序列化,必须要有,不然获取不到content里面的accessID等
     * 需要在oss函数计算里授权
     * @param context
     * @throws IOException
     */
    @Override
    public void initialize(Context context) throws IOException {
        FunctionComputeLogger logger = context.getLogger();
        logger.debug(String.format("RequestID is %s %n", context.getRequestId()));
    }
}