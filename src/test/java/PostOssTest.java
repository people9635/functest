import DTO.AnnexFunctionDTO;
import entity.SimpleRequestEntity;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: shuangxi
 * @create: 2018/12/14上午11:12
 */

public class PostOssTest {

    @Test
    public void testOssPost() throws Exception {
        String hosts ="";
        String path = "";
        String method ="POST";
        //参数构建
        String endpoint = "";
        String bucketName = "";
        SimpleRequestEntity simpleRequestEntity = new SimpleRequestEntity();
        simpleRequestEntity.setBucket(bucketName);
        simpleRequestEntity.setEndpoint(endpoint);
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
        simpleRequestEntity.setGroupName("other");
        JSONObject jsonObject = JSONObject.fromObject(simpleRequestEntity);
        Map map = new HashMap();
        map.put("Content-Type","application/octet-stream");
        String body = jsonObject.toString();
        HttpResponse h  = HttpUtils.doPost(hosts,path,method,map,map,body);
        String output = EntityUtils.toString(h.getEntity());
        System.out.println(output);
    }



    @Test
    public void testOssPost2() throws Exception {
        String hosts ="https://1769906539081995.cn-shanghai.fc.aliyuncs.com/";
        String path = "2016-08-15/proxy/pic_package_download/suzuki_dev_f/";
        String method ="POST";
        //参数构建
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String bucketName = "pcl-suzuki";
        SimpleRequestEntity simpleRequestEntity = new SimpleRequestEntity();
        simpleRequestEntity.setBucket(bucketName);
        simpleRequestEntity.setEndpoint(endpoint);
        simpleRequestEntity.setWatermark("物产集成 9477");
        List<AnnexFunctionDTO> annexFunctionDTOList = new ArrayList<>();
        AnnexFunctionDTO annexFunctionDTO1 = new AnnexFunctionDTO();
        annexFunctionDTO1.setKey("family/1264b40106df6914f4a8b5079262c715.jpg");
        annexFunctionDTO1.setFileDir("CI_201812257153二手车1/FAMILY/");
        annexFunctionDTO1.setFileName("1_1264b40106df6914f4a8b5079262c715.jpg");
        annexFunctionDTOList.add(annexFunctionDTO1);
        AnnexFunctionDTO annexFunctionDTO2 = new AnnexFunctionDTO();
        annexFunctionDTO2.setKey("sponsor/edd9dd0e538b701e2cf5d564503428d2.jpg");
        annexFunctionDTO2.setFileDir("CI_201812257153二手车1/SPONSOR/");
        annexFunctionDTO2.setFileName("1_edd9dd0e538b701e2cf5d564503428d2.jpg");
        annexFunctionDTOList.add(annexFunctionDTO2);
        simpleRequestEntity.setKeyList(annexFunctionDTOList);
        simpleRequestEntity.setGroupName("other");
        JSONObject jsonObject = JSONObject.fromObject(simpleRequestEntity);
        Map map = new HashMap();
        Map header = new HashMap();
        header.put("Content-Type","application/octet-stream");
        header.put("Content-Length",32);
//        String body = jsonObject.toString();
        String body = "{\"bucket\":\"pcl-suzuki\",\"endpoint\":\"oss-cn-hangzhou.aliyuncs.com\",\"groupName\":\"orderZip\",\"watermark\":\"物产集成 9477\",\"keyList\":[{\"fileName\":\"1_1264b40106df6914f4a8b5079262c715.jpg\",\"fileDir\":\"CI_201812257153二手车1/FAMILY/\",\"key\":\"family/1264b40106df6914f4a8b5079262c715.jpg\"},{\"fileName\":\"1_edd9dd0e538b701e2cf5d564503428d2.jpg\",\"fileDir\":\"CI_201812257153二手车1/SPONSOR/\",\"key\":\"sponsor/edd9dd0e538b701e2cf5d564503428d2.jpg\"},{\"fileName\":\"1_970fe1b52b0b671d4b057760ebbc8771.jpg\",\"fileDir\":\"CI_201812257153二手车1/ASSETS/\",\"key\":\"assets/970fe1b52b0b671d4b057760ebbc8771.jpg\"},{\"fileName\":\"1_a0efadd053d69c22b2c0ce32801bc6c7.jpg\",\"fileDir\":\"CI_201812257153二手车1/FINANCE/\",\"key\":\"finance/a0efadd053d69c22b2c0ce32801bc6c7.jpg\"},{\"fileName\":\"1_82eaaf4fa6ccba84b3eb1a7f79afa16f.jpg\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/82eaaf4fa6ccba84b3eb1a7f79afa16f.jpg\"},{\"fileName\":\"1_912d4b1160047c3839d1034b5f322db2.mp4\",\"fileDir\":\"CI_201812257153二手车1/VIDEO/\",\"key\":\"video/912d4b1160047c3839d1034b5f322db2.mp4\"},{\"fileName\":\"1_b1d8831032a4cfc8668a7fb5ca62ffda.wav\",\"fileDir\":\"CI_201812257153二手车1/AUDIO/\",\"key\":\"audio/b1d8831032a4cfc8668a7fb5ca62ffda.wav\"},{\"fileName\":\"2_d435f2dab8886c2c56c23dc11214aa4e.jpg\",\"fileDir\":\"CI_201812257153二手车1/FAMILY/\",\"key\":\"family/d435f2dab8886c2c56c23dc11214aa4e.jpg\"},{\"fileName\":\"2_8c5a310ec7b5a165bc46e0e264dd54cc.jpg\",\"fileDir\":\"CI_201812257153二手车1/SPONSOR/\",\"key\":\"sponsor/8c5a310ec7b5a165bc46e0e264dd54cc.jpg\"},{\"fileName\":\"2_b45eae1a2941eda4cf287930e88907b3.jpg\",\"fileDir\":\"CI_201812257153二手车1/ASSETS/\",\"key\":\"assets/b45eae1a2941eda4cf287930e88907b3.jpg\"},{\"fileName\":\"2_cab763f31a4e5b6a259a43a69b799397.jpg\",\"fileDir\":\"CI_201812257153二手车1/FINANCE/\",\"key\":\"finance/cab763f31a4e5b6a259a43a69b799397.jpg\"},{\"fileName\":\"2_3b985b6d6f7342bc5f99b88087a6b792.jpg\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/3b985b6d6f7342bc5f99b88087a6b792.jpg\"},{\"fileName\":\"2_10fda3115a5d48cdcd1eeb7b4d116cf2.ogg\",\"fileDir\":\"CI_201812257153二手车1/AUDIO/\",\"key\":\"audio/10fda3115a5d48cdcd1eeb7b4d116cf2.ogg\"},{\"fileName\":\"2_b440a392bfa8050824a949f17878de3f.mp4\",\"fileDir\":\"CI_201812257153二手车1/VIDEO/\",\"key\":\"video/b440a392bfa8050824a949f17878de3f.mp4\"},{\"fileName\":\"3_f03ab90b449019576aa41d272ebef8ed.jpg\",\"fileDir\":\"CI_201812257153二手车1/FAMILY/\",\"key\":\"family/f03ab90b449019576aa41d272ebef8ed.jpg\"},{\"fileName\":\"3_b7b881f475910a8a96ad222711163f5e.jpg\",\"fileDir\":\"CI_201812257153二手车1/SPONSOR/\",\"key\":\"sponsor/b7b881f475910a8a96ad222711163f5e.jpg\"},{\"fileName\":\"3_1e8975b8c6d5c10a2cf1e6a839f1fabf.jpg\",\"fileDir\":\"CI_201812257153二手车1/ASSETS/\",\"key\":\"assets/1e8975b8c6d5c10a2cf1e6a839f1fabf.jpg\"},{\"fileName\":\"3_b137d7f6b2484c4ebdbfd9898e3c9a20.jpg\",\"fileDir\":\"CI_201812257153二手车1/FINANCE/\",\"key\":\"finance/b137d7f6b2484c4ebdbfd9898e3c9a20.jpg\"},{\"fileName\":\"3_9c09dd87e45e9c1005bc8ecfe750df71.jpg\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/9c09dd87e45e9c1005bc8ecfe750df71.jpg\"},{\"fileName\":\"3_67c43535b227fbe501e8cae3c2bf7a35.mp3\",\"fileDir\":\"CI_201812257153二手车1/AUDIO/\",\"key\":\"audio/67c43535b227fbe501e8cae3c2bf7a35.mp3\"},{\"fileName\":\"3_9dbf2606c9571cdd377b5a855bf357ad.mp4\",\"fileDir\":\"CI_201812257153二手车1/VIDEO/\",\"key\":\"video/9dbf2606c9571cdd377b5a855bf357ad.mp4\"},{\"fileName\":\"4_354f5e1d6d730a108f232617ee4cb1dd.jpg\",\"fileDir\":\"CI_201812257153二手车1/FAMILY/\",\"key\":\"family/354f5e1d6d730a108f232617ee4cb1dd.jpg\"},{\"fileName\":\"4_848ea6d896f9b2cecde8f12755d2a473.jpg\",\"fileDir\":\"CI_201812257153二手车1/SPONSOR/\",\"key\":\"sponsor/848ea6d896f9b2cecde8f12755d2a473.jpg\"},{\"fileName\":\"4_47b658676e9e3f83592e341e3fa5f92f.jpg\",\"fileDir\":\"CI_201812257153二手车1/ASSETS/\",\"key\":\"assets/47b658676e9e3f83592e341e3fa5f92f.jpg\"},{\"fileName\":\"4_90a244b8804e9b6121a12dbfcec3d316.jpg\",\"fileDir\":\"CI_201812257153二手车1/FINANCE/\",\"key\":\"finance/90a244b8804e9b6121a12dbfcec3d316.jpg\"},{\"fileName\":\"4_8d4ec80d2a64096b4bf89fbced9b3cc7.jpg\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/8d4ec80d2a64096b4bf89fbced9b3cc7.jpg\"},{\"fileName\":\"4_357692f56ee3e810b987911ad81a3e37.mp4\",\"fileDir\":\"CI_201812257153二手车1/VIDEO/\",\"key\":\"video/357692f56ee3e810b987911ad81a3e37.mp4\"},{\"fileName\":\"5_daa62169fdbf8cebd92f35c993b95092.jpg\",\"fileDir\":\"CI_201812257153二手车1/FAMILY/\",\"key\":\"family/daa62169fdbf8cebd92f35c993b95092.jpg\"},{\"fileName\":\"5_01175991449b40465304e54cdfbc8f43.jpg\",\"fileDir\":\"CI_201812257153二手车1/SPONSOR/\",\"key\":\"sponsor/01175991449b40465304e54cdfbc8f43.jpg\"},{\"fileName\":\"5_80b205cb361b2c622f4a2787c5aae0fc.jpg\",\"fileDir\":\"CI_201812257153二手车1/ASSETS/\",\"key\":\"assets/80b205cb361b2c622f4a2787c5aae0fc.jpg\"},{\"fileName\":\"5_33aed7a38c3d85ee0825436e93a87c9d.jpg\",\"fileDir\":\"CI_201812257153二手车1/FINANCE/\",\"key\":\"finance/33aed7a38c3d85ee0825436e93a87c9d.jpg\"},{\"fileName\":\"5_5c0d9c0d2e22e949bae7eb0e186a3001.jpg\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/5c0d9c0d2e22e949bae7eb0e186a3001.jpg\"},{\"fileName\":\"7_4bf424ac9080742164dc70ec8428b080.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/4bf424ac9080742164dc70ec8428b080.png\"},{\"fileName\":\"10_069c02a653db29269a2e2c56a4b7dba9.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/069c02a653db29269a2e2c56a4b7dba9.png\"},{\"fileName\":\"11_a266b3041bf23501b10c66f63a8c4c84.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/a266b3041bf23501b10c66f63a8c4c84.png\"},{\"fileName\":\"23_487a966bc6b03a576785c76aa8668db6.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/487a966bc6b03a576785c76aa8668db6.png\"},{\"fileName\":\"25_832f903fb7015f8b2e9f87bccf622bc4.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/832f903fb7015f8b2e9f87bccf622bc4.png\"},{\"fileName\":\"26_775d8bd825cfc467d45dee52231ea484.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/775d8bd825cfc467d45dee52231ea484.png\"},{\"fileName\":\"27_2206e82d646143a24103f2a84524474f.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/2206e82d646143a24103f2a84524474f.png\"},{\"fileName\":\"28_1ae48945c0becae0e8dbd8186d83c1a7.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/1ae48945c0becae0e8dbd8186d83c1a7.png\"},{\"fileName\":\"29_201b16b9aca6c10589b4bc3879f9334e.png\",\"fileDir\":\"CI_201812257153二手车1/OTHER/\",\"key\":\"other/201b16b9aca6c10589b4bc3879f9334e.png\"}]}";
        HttpResponse h  = HttpUtils.doPost(hosts,path,method,map,map,body);
        String output = EntityUtils.toString(h.getEntity());
        System.out.println(output);
    }
}