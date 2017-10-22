package cn.llf.framework.gateway.intercept;

import cn.llf.mybatis.support.Page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Author：calvin
 * Date:  2017/10/22 0022
 * 描述：
 */
public class WriteFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("status",true);
        if (obj instanceof Page){
            Page page = (Page) obj;
            jsonObject.put("totalSize",page.getTotalSize());
            jsonObject.put("totalPageSize",page.getTotalPageSize());
            jsonObject.put("info",page.getCurrentPageData());
        }else {
            jsonObject.put("info",obj);
        }
        String text = JSON.toJSONString(jsonObject, getFeatures());
        byte[] bytes = text.getBytes(getCharset());
        out.write(bytes);
        out.close();
    }
}
