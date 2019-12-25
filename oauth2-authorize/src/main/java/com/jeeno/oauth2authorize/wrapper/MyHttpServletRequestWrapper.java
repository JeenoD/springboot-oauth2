package com.jeeno.oauth2authorize.wrapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 请求封装类（支持从请求中提取body内的参数）
 * @author Jeeno
 * @version 2.1.0
 * @date 2019/12/4 9:43
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;

    /**
     * 将json参数内容转换成JSONObject
     * @return JSONObject
     */
    public JSONObject getRequestBody() {
        return JSON.parseObject((new String(requestBody, StandardCharsets.UTF_8)));
    }

    @SuppressWarnings("unused")
    public void setRequestBody(JSONObject jsonObject) {
        this.requestBody = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);
    }

    public MyHttpServletRequestWrapper(HttpServletRequest request) {

        super(request);

        try {
            requestBody = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream(){
        if (requestBody == null) {
            requestBody = new byte[0];
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
}
