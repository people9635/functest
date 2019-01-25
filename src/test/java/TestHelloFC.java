import DTO.AnnexFunctionDTO;
import com.aliyun.fc.runtime.*;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import entity.SimpleRequestEntity;
import entity.SimpleResponseEntity;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import util.CompressedFileUtil;
import util.DownloadnUtil;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipOutputStream;


public class TestHelloFC {
//    public SimpleResponseEntity handleRequest() {
//        String endpoint = "oss-cn-shanghai.aliyuncs.com";
//        String bucketName = "xujiawei";
//        OSSClient client = new OSSClient(
//                "oss-cn-shanghai.aliyuncs.com", "LTAIIndul6r2Ay2B", "HPUBY3tRIZ9Npdo6LhJU9IDTwBhkkV");
//        List<String> keys = new ArrayList<>();
//        keys.add("aaa/1.jpg");
//        keys.add("aaa/2.jpg");
//        List<Map<String,String>> urls = new ArrayList<>();
//        if (CollectionUtils.isEmpty(keys)){
//            return null;
//        }
//        for (String key:keys){
//            Map<String,String> map = new HashMap();
//            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, "aaa/1.jpg");
//            long expireEndTime = System.currentTimeMillis() + 60*60 * 1000;
//            Date expiration = new Date(expireEndTime);
//            map.put("x-oss-process","image/resize,m_fixed,h_400,w_400/watermark,color_FFFFFF,interval_100,size_20,fill_1,t_15,rotate_330,text_5rWZ5rGf54mp5Lqn5L-h5oGv57O757uf6ZuG5oiQ5pyJ6ZmQ5YWs5Y-4DQo");
//            generatePresignedUrlRequest.setQueryParameter(map);
//            generatePresignedUrlRequest.setExpiration(expiration);
//            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
//            URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
//            map.put("key",key);
//            map.put("url",url.toString());
//            urls.add(map);
//        }
//        String targetDir = "/tmp/download/";
//        for (Map<String,String> urlMap:urls) {
//            try {
//                File file = new File(targetDir);
//                file.mkdirs();
//                //获取文件名
//                String fileName = urlMap.get("key").substring(urlMap.get("key").lastIndexOf("/")+1);
//                DownloadnUtil.fetchContent(urlMap.get("url"), targetDir+fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        CompressedFileUtil compressedFileUtil = new CompressedFileUtil();
//        String targetName = null;
//        try {
//            targetName = compressedFileUtil.compressedFile(targetDir, "/tmp/");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        File file = new File("/tmp/"+targetName);
//        client.putObject(bucketName,"suzukiTest.zip",file);
//        return new SimpleResponseEntity(targetName);
//    }

//    public static void main(String[] args) {
//        TestHelloFC testHelloFC = new TestHelloFC();
//        testHelloFC.handleRequest();
//    }
    public static void main(String[] args) {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        String bucketName = "xujiawei";
        SimpleRequestEntity simpleRequestEntity = new SimpleRequestEntity();
        simpleRequestEntity.setBucket(endpoint);
        simpleRequestEntity.setEndpoint(bucketName);
        simpleRequestEntity.setWatermark("水水水水印");
        List<AnnexFunctionDTO> annexFunctionDTOList = new ArrayList<>();
        AnnexFunctionDTO annexFunctionDTO1 = new AnnexFunctionDTO();
        annexFunctionDTO1.setKey("aaa/1.jpg");
        annexFunctionDTO1.setFileDir("1_order_no");
        annexFunctionDTO1.setFileName("1.jpg");
        annexFunctionDTOList.add(annexFunctionDTO1);
        AnnexFunctionDTO annexFunctionDTO2 = new AnnexFunctionDTO();
        annexFunctionDTO2.setKey("aaa/2.jpg");
        annexFunctionDTO2.setFileDir("2_order_no");
        annexFunctionDTO2.setFileName("2.jpg");
        annexFunctionDTOList.add(annexFunctionDTO2);
        simpleRequestEntity.setKeyList(annexFunctionDTOList);
        simpleRequestEntity.setGroupName("hello");
        JSONObject jsonObject = JSONObject.fromObject(simpleRequestEntity);
        System.out.println(jsonObject.toString());

    }


}