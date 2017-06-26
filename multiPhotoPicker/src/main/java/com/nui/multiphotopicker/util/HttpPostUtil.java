package com.nui.multiphotopicker.util;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.alibaba.fastjson.JSON;

public class HttpPostUtil {
	//private static DengRuData dengRuData;
	public static String data = "";
	static File uploadFile;
	public static List<Map<String, String>> username_list;

	/** 保存头像 */
	public static String saveimage(final String uploaded_picture[]) 
	{
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				String urlStr = "你的接口";
				HttpPost httpPost = new HttpPost(urlStr);
				try {
					uploadFile = new File(uploaded_picture[0]);
					httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
					MultipartEntity mpEntity = new MultipartEntity(); // 文件传输
					ContentBody cbFile = new FileBody(uploadFile);
					mpEntity.addPart("uploaded_picture", cbFile);
					httpPost.setEntity(mpEntity);
					// httpClient执行httpPost提交
					HttpResponse response = httpClient.execute(httpPost);
					// HttpResponse response =
					// filterExecutePost(httpClient,httpPost);
					// 得到服务器响应实体对象
					HttpEntity responseEntity = response.getEntity();
					if (responseEntity != null) {
						/** 服务器返回 */
						data = EntityUtils.toString(responseEntity, "utf-8");
						Log.e("我爱你", data);
					//	dengRuData = JSON.parseObject(data, DengRuData.class);
						//String id = dengRuData.getData();
						//int setStatus = dengRuData.getStatus();
							if (uploaded_picture.length > 1) {
								for (int i = 1; i < uploaded_picture.length; i++) {
									http_file(uploaded_picture[i]);
							}
						}
					}
					if (responseEntity == null) {
						Log.e("------->", "值还没加载出来");
					}
				} catch (Exception e) {
				} finally {
					// 释放资源
					httpClient.getConnectionManager().shutdown();
				}
			}
		}).start();
		return data;
	}
	public static String http_file(final String uploaded_picture) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();
				String urlStr = "你的接口";
				HttpPost httpPost = new HttpPost(urlStr);
				try {
					uploadFile = new File(uploaded_picture);
					httpClient.getParams().setParameter(
							CoreProtocolPNames.PROTOCOL_VERSION,
							HttpVersion.HTTP_1_1);
					MultipartEntity mpEntity = new MultipartEntity(); // 文件传输
					ContentBody cbFile = new FileBody(uploadFile);
					mpEntity.addPart("uploaded_picture", cbFile); // <input type="file"
														// name="userfile" />
														// 对应的
					httpPost.setEntity(mpEntity);
					// httpClient执行httpPost提交
					HttpResponse response = httpClient.execute(httpPost);
					// HttpResponse response =
					// filterExecutePost(httpClient,httpPost);
					// 得到服务器响应实体对象
					HttpEntity responseEntity = response.getEntity();
					if (responseEntity != null) {
						/** 服务器返回 */
						data = EntityUtils.toString(responseEntity, "utf-8");
					}
					if (responseEntity == null) {
						Log.e("------->", "值还没加载出来");
					}
				} catch (Exception e) {
				} finally {
					// 释放资源
					httpClient.getConnectionManager().shutdown();
				}
			}
		}).start();
		return data;
	}
}