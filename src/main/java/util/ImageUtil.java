package util;

import lombok.Data;

/**
 * @Description:
 * @author: shuangxi
 * @create: 2018/12/13下午7:28
 */
@Data
public class ImageUtil {
    /**
     * 判断是否是图片
     * @param name
     * @return
     */
    public static Boolean isPic(String name){
        String reg="(?i).+?\\.(jpg|bmp|png|jpeg)";
        return  name.matches(reg);
    }
}
