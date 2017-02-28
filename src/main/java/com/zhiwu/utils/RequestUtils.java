package com.zhiwu.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * HTTP请求工具类
 * 
 * @author Loki_yb
 * 
 * @since 2016年5月19日
 * 
 **/

public class RequestUtils {
	protected static Logger logger = Logger.getLogger(RequestUtils.class);
	private static HttpClient hc = new DefaultHttpClient();  //初始化一个HTTP的客户端对象


	//功能: Get形式发送请求
	//@param url 请求地址
	//@param params 请求中传递的参数
	//@return
	@SuppressWarnings("deprecation")
	public static String get(String url, List params) {
		String body = null;
		try {
			// Get请求
			HttpGet httpget = new HttpGet(url);
			// 设置参数
			String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
			httpget.setURI(new java.net.URI(httpget.getURI().toString() + "?" + str));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httpget);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			if (entity != null) {
				entity.consumeContent();
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return body;
	}

	
	//功能: postBody形式发送数据
	//@param urlPath 对方地址
	//@param json 要传送的数据
	//@return
	//@throws Exception
	public static String postBody(String url, String param) {
		//JSONObject jsonObject = JSONObject.fromMap(param);
		//String json = JSONObject.toJSONString(param);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection  conn = (HttpURLConnection)realUrl.openConnection();
          
            // 设置通用的请求属性
			conn.setRequestProperty("Content-Type", "application/json; encoding=UTF-8;charset=UTF-8");
			//conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.write(URLEncoder.encode(param,"UTF-8"));
            // flush输出流的缓冲
            out.flush();
            out.close();
            // 定义BufferedReader输入流来读取URL的响应
            conn.connect();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            int code = conn.getResponseCode();
            result += code;
        } catch (Exception e) {
            logger.error("发送 POST 请求出现异常!"+e);
        }
        return result;
    }


}
