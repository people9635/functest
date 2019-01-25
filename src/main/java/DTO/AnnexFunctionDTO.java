package DTO;

import lombok.Data;

/**
 * @Description:阿里云OSS,打包附件函数实体类
 * @author: shuangxi
 * @create: 2018/12/13下午6:55
 */
@Data
public class AnnexFunctionDTO {
    /**
     * 文件名路径
     */
    private String fileDir;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * oss文件的key
     */
    private String key;

    /**
     * oss文件url
     */
    private String url;



}
