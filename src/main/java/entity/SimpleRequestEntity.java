package entity;

import DTO.AnnexFunctionDTO;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: shuangxi
 * @create: 2018/12/12下午5:40
 */
@Data
public class SimpleRequestEntity {
    /**
     * 文件信息列表
     */
    private List<AnnexFunctionDTO> keyList;
    /**
     * oss - endpoint
     */
    private String endpoint;
    /**
     * oss = bucket
     */
    private String bucket;

    /**
     * 图片水印内容,不传就不加水印
     */
    private String watermark;

    /**
     * 合成的zip存放分组
     */
    private String groupName;



    public SimpleRequestEntity() {}


    public SimpleRequestEntity(List<AnnexFunctionDTO> keyList,String endpoint,String bucket) {
        this.keyList = keyList;
        this.endpoint = endpoint;
        this.bucket = bucket;
    }
}
