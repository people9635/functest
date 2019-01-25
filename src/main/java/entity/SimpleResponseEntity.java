package entity;

import lombok.Data;

/**
 * @Description: 返回实体类
 * @author: shuangxi
 * @create: 2018/12/12下午5:42
 */
@Data
public class SimpleResponseEntity {
    /**
     * 最终合成zip,上传后的key
     */
    private String key;
    /**
     * 最终合成zip的公网加密链接
     */
    private String url;
    /**
     * 返回异常消息,无异常时为空
     */
    private String msg;

    public SimpleResponseEntity() {}

    public SimpleResponseEntity(String key,String url,String msg) {
        this.key = key;
        this.url = url;
        this.msg = msg;
    }
}
