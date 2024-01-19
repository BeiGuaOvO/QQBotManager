package github.beiguaovo.qqbotmanager.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class HttpRequestUtil {
    /**
     * @Description: 发送get请求
     */
    public static String doGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("DataEncoding", "UTF-8");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(60000).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            if(httpResponse.getStatusLine().getStatusCode() != 200){
                return null;
            }
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e.getMessage());
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    log.error(e.getMessage());
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * @Description: 发送http post请求
     */
    public static String doPost(String url, String jsonStr) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000).setConnectionRequestTimeout(35000).setSocketTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("DataEncoding", "UTF-8");
        CloseableHttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new StringEntity(jsonStr));
            httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode() != 200){
                return null;
            }
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return null;
    }
}
