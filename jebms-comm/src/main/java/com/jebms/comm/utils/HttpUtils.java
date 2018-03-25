package com.jebms.comm.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 提交Http Request
 * 
 * @author samt007
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	//https://www.cnblogs.com/wangtj-19/p/5889056.html
	public static JSONObject httpPost(String urlPath,JSONObject postParams,Map<String,String> reqHeaders) throws Exception{
	    URL url;
	    HttpURLConnection connection = null;
		JSONObject jsonObject = null;
        StringBuffer sb = new StringBuffer(""); 
	     try { 
             //创建连接 
             url = new URL(urlPath); 
             connection = (HttpURLConnection) url.openConnection(); 
             connection.setDoOutput(true); 
             connection.setDoInput(true); 
             connection.setRequestMethod("POST"); 
             connection.setUseCaches(false); 
             connection.setInstanceFollowRedirects(true); 
             for (Map.Entry<String, String> entry : reqHeaders.entrySet()) {  
            	 //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
            	 connection.setRequestProperty(entry.getKey(), entry.getValue());
             }
             //connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
             //connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");//解决返回415的问题
             //connection.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJXWDIxNDQ5MiIsImV4cCI6MTUxMTQyNTMyMn0.JE9GGPcXmvViZCB1oyrqG0l3ZxVamcdooPne7GImqLXWGkSzqqAqgv_x_8qRA0DSi0oHpJsV6KcwT31Z0r5C-w");
             connection.connect(); 
             //POST请求 
             DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
             logger.info(JSON.toJSONStringWithDateFormat(postParams, "yyyy-MM-dd HH:mm:ss")); 
             out.write(JSON.toJSONStringWithDateFormat(postParams, "yyyy-MM-dd HH:mm:ss").getBytes("UTF-8"));
             out.flush(); 
             out.close(); 
             //读取响应 
             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
             String lines; 
             while ((lines = reader.readLine()) != null) { 
                 lines = new String(lines.getBytes(), "utf-8"); 
                 sb.append(lines); 
             } 
             logger.info(sb.toString());//System.out.println(sb); 
             jsonObject = JSONObject.parseObject(sb.toString());
             reader.close(); 
         } finally {// 断开连接 
             if(connection != null) {
            	 connection.disconnect(); 
             }
           }
         return jsonObject;
	}
	
}
