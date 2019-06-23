package cn.llf.http;

import cn.llf.framework.gateway.web.admin.dto.CourseDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eleven
 * @date 2019/6/22
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class HttpClientUtil {

    @Test
    public void doGet(){
        log.info("1111111");
        String url = "http://127.0.0.1:8080/web/admin/course/course";
        Map<String,String> param = new HashMap<>();
        param.put("id","123123");
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info(">>>>>>>>>>>>>>>resultString:[{}]",resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    @Test
    public void doPost(){
        log.info("1111111");
        String url = "http://127.0.0.1:8080/web/admin/course/addCourse";
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();
            // 创建http Post请求
            HttpPost httpPost = new HttpPost(uri);
            //构建请求参数
            CourseDTO query = new CourseDTO();
            query.setId("12312");
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(query),ContentType.APPLICATION_JSON));
            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.info(">>>>>>>>>>>>>>>resultString:[{}]",resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
