package com.wenwl.my.shop.commons.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author wenwl
 * @className HttpClientUtils
 * @dsecription HttpClient工具类
 * @data 2020/4/6 12:17
 * @vserion 1.0.0
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36";

    /**
     * GET 请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return createRequest(url, GET, null);
    }

    /**
     * GET 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie) {
        return createRequest(url, GET, cookie);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return createRequest(url, POST, null, params);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, String cookie, BasicNameValuePair... params) {
        return createRequest(url, POST, cookie, params);
    }

    /**
     * 创建请求
     *
     * @param url           请求地址
     * @param requestMethod 请求方式 GET/POST
     * @param cookie        cookie
     * @param params        请求参数 仅限于 POST 请求用
     * @return
     */
    private static String createRequest(String url, String requestMethod, String cookie, BasicNameValuePair... params) {

        String result = null;

        try {
            // 请求结果
            result = null;

            // 响应
            Response response = null;

            // GET 请求
            if (GET.equals(requestMethod)) {
                Request request = Request.Get(url)
                        .addHeader("Connection", REQUEST_HEADER_CONNECTION)
                        .addHeader("Cookie", cookie)
                        .setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                response = request.execute();
            }

            // POST 请求
            else if (POST.equals(requestMethod)) {
                Request request = Request.Post(url)
                        .addHeader("Connection", REQUEST_HEADER_CONNECTION)
                        .addHeader("Cookie", cookie)
                        .setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);

                // 有参数进来
                if (params != null && params.length > 0) {
                    request.bodyForm(Arrays.asList(params), Consts.UTF_8);
                }

                response = request.execute();
            }

            if(response != null){
                result = response.returnContent().toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
