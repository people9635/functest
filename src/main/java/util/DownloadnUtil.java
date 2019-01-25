package util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * @Description:
 * @author: shuangxi
 * @create: 2018/12/13上午9:51
 */
public class DownloadnUtil {

    private static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

    private static final int TIMEOUT_SECONDS = 120;

    private static final int POOL_SIZE = 120;



    public static void fetchContent(String imageUrl, String filePath) throws IOException {
        CloseableHttpClient httpclient = initApacheHttpClient();
        HttpGet httpget = new HttpGet(imageUrl);
        httpget.setHeader("Referer", "http://www.google.com");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() >= 400) {
                throw new IOException("Got bad response, error code = " + response.getStatusLine().getStatusCode()
                        + " imageUrl: " + imageUrl);
            }
            if (entity != null) {
                InputStream input = entity.getContent();
                OutputStream output = new FileOutputStream(new File(filePath));
                IOUtils.copy(input, output);
                output.flush();
            }
        } finally {
            response.close();
            destroyApacheHttpClient(httpclient);
        }

    }



    public static CloseableHttpClient initApacheHttpClient() {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_SECONDS * 1000)
                .setConnectTimeout(TIMEOUT_SECONDS * 1000).build();
        CloseableHttpClient httpclient = HttpClients.custom().setUserAgent(USER_AGENT).setMaxConnTotal(POOL_SIZE)
                .setMaxConnPerRoute(POOL_SIZE).setDefaultRequestConfig(defaultRequestConfig).build();
        return httpclient;
    }

    public static void destroyApacheHttpClient(CloseableHttpClient httpclient) {
        try {
            httpclient.close();
        } catch (IOException e) {
        }
    }
}
