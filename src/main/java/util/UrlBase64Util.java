package util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;


/**
 * @Description: 阿里云要求的,水印需要用安全的base64加密
 * @author: shuangxi
 * @create: 2018/12/10下午7:13
 */
public class UrlBase64Util {

    private static final BASE64Encoder encoder = new BASE64Encoder();
    private static final BASE64Decoder decoder = new BASE64Decoder();


    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String key) {
        if (key == null || key.length() < 1) {
            return "";
        }
        try {
            return new String(decoder.decodeBuffer(new String(Base64.decodeBase64(key.getBytes()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String key){
        if (key == null || key.length() < 1) {
            return "";
        }
        return new String(Base64.encodeBase64URLSafe(key.getBytes()));
    }
}
