package Enum;

/**
 * 申请状态
 * Created by HMJ on 2017/9/28.
 */
public enum OssPicTransEnum {
    // 100*100
    IMAGE_100_100(0, "image/resize,m_fixed,h_100,w_100,"),
    // 200*200
    IMAGE_200_200(1, "image/resize,m_fixed,h_200,w_200,"),
    // 400*400
    IMAGE_400_400(2, "image/resize,m_fixed,h_400,w_400/watermark,color_FFFFFF,interval_100,size_20,fill_1,t_15,rotate_330,text_"),
    // 限宽1200+水印
    IMAGE_REAL(3, "image/resize,w_1200/watermark,color_FFFFFF,size_30,t_15,rotate_330,g_nw,text_"+
            "/watermark,color_FFFFFF,size_30,t_15,rotate_330,g_ne,text_" +
            "/watermark,color_FFFFFF,size_30,t_15,rotate_330,g_center,text_" +
            "/watermark,color_FFFFFF,size_30,t_15,rotate_330,g_sw,text_" +
            "/watermark,color_FFFFFF,size_30,t_15,rotate_330,g_se,text_"),
    //视频截帧
    VIDEO_SHUTCUT(4,"video/snapshot,t_1000,f_jpg,w_800,h_600,m_fast"),
    IMAGE_1200_M(5, "image/resize,m_fixed,w_1200/watermark,color_FFFFFF,size_50,t_15,rotate_330,g_nw,text_"+
                       "/watermark,color_FFFFFF,size_50,t_15,rotate_330,g_ne,text_" +
                       "/watermark,color_FFFFFF,size_50,t_15,rotate_330,g_center,text_" +
                       "/watermark,color_FFFFFF,size_50,t_15,rotate_330,g_sw,text_" +
                       "/watermark,color_FFFFFF,size_50,t_15,rotate_330,g_se,text_");
    private Integer code;
    private String name;

    OssPicTransEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getNameByCode(Integer code){
        for(OssPicTransEnum item : OssPicTransEnum.values()){
            if(item.getCode().equals(code)){
                return item.getName();
            }
        }
        return "";
    }

}
