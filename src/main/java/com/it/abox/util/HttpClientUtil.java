package com.it.abox.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


/**
 * @author liu
 * */
public class HttpClientUtil {
	
	static final String CODE = "UTF-8";
	static final String JSON_TYPE = "application/json";
	static final String TEXT_TYPE = "text/plain";
	
	static private RequestConfig requestConfig = RequestConfig.custom()  
            .setSocketTimeout(15000)  
            .setConnectTimeout(15000)  
            .setConnectionRequestTimeout(15000)  
            .build();
	
	public static byte[] get(String url){
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		HttpGet httpget = new HttpGet(url); 
		httpget.setConfig(requestConfig);
		CloseableHttpResponse response = null;
		InputStream in = null;
		byte[] bs = null;
		try {  
			response = httpclient.execute(httpget);
			in = doResponse(response);
			bs = readStream(in);
		} catch (IOException e){
			e.printStackTrace();
		}finally {
			close(httpclient,response, in);
		} 
		return bs;
	}
	
	/** 
     * 发送 post请求 
     * @param httpUrl 地址 
     * @param maps 参数 
     */  
    public static byte[] sendHttpPost(String httpUrl, Map<String, String> maps) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        // 创建参数队列    
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        for (String key : maps.keySet()) {  
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));  
        }  
        try {  
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, CODE));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return sendHttpPost(httpPost);  
    }  
    
    /** 
     * 发送 post请求（带文件） 
     * @param httpUrl 地址 
     * @param maps<name, value> 参数 
     * @param files<fileName, file> 附件 
     */  
    public static byte[] sendHttpPost(String httpUrl, Map<String, String> maps, Map<String, File> files) {  
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost    
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        for (String key : maps.keySet()) {  
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.MULTIPART_FORM_DATA));  
        }
        meBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for(String fileName : files.keySet()) {
        	File file = files.get(fileName);
            FileBody fileBody = new FileBody(file);  
            meBuilder.addPart(fileName, fileBody);  
        }  
        HttpEntity entity = meBuilder.build();  
        httpPost.setEntity(entity);  
        return sendHttpPost(httpPost);  
    }
    
    public static byte[] sendHttpInputStream(String httpUrl, Map<String, String> params, List<InputStreamPost> inPosts){
    	HttpPost httpPost = new HttpPost(httpUrl);
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();  
        for (String key : params.keySet()) {  
            try {
				meBuilder.addPart(key, new StringBody(URLEncoder.encode(params.get(key), CODE), 
						ContentType.MULTIPART_FORM_DATA));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
        }
        meBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for(InputStreamPost inpost: inPosts){
        	try {
				meBuilder.addBinaryBody(URLEncoder.encode(inpost.getPostName(), CODE), inpost.getInputStream(), 
						inpost.getContentType(), URLEncoder.encode(inpost.getFileName(), CODE));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
        HttpEntity entity = meBuilder.build();  
        httpPost.setEntity(entity);
        return sendHttpPost(httpPost);
    }
    
    public static class InputStreamPost{
    	
    	/**((MultipartHttpServletRequest)request).getFile(postName)*/
    	private String postName;
    	
    	private String fileName;
    	
    	private InputStream inputStream;
    	
    	/**
    	 * such as "application/zip"
    	 * */
    	private ContentType contentType = ContentType.DEFAULT_BINARY;
    	
    	public InputStreamPost(){
    		
    	}
    	
    	public InputStreamPost(String postName, InputStream inputStream){
    		this(postName, postName, inputStream);
    	}
    	
    	public InputStreamPost(String postName, String fileName, InputStream inputStream){
    		this.postName = postName;
    		this.fileName = fileName;
    		this.inputStream = inputStream;
    	}

		public String getPostName() {
			return postName;
		}

		public void setPostName(String postName) {
			this.postName = postName;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public ContentType getContentType() {
			return contentType;
		}

		public void setContentType(ContentType contentType) {
			this.contentType = contentType;
		}
    }
	
	static byte[] sendHttpPost(HttpPost httpPost) {  
        CloseableHttpClient httpClient = null;  
        CloseableHttpResponse response = null;  
        InputStream in = null;
		byte[] bs = null;
        try {  
            // 创建默认的httpClient实例.  
            httpClient = HttpClients.createDefault();  
            httpPost.setConfig(requestConfig);  
            // 执行请求  
            response = httpClient.execute(httpPost); 
            in = doResponse(response);
			bs = readStream(in);
        } catch (Throwable e) {  
            e.printStackTrace();  
        } finally {  
            close(httpClient, response, in);
        }  
        return bs;  
    }  
	
	static void close(CloseableHttpClient httpclient, CloseableHttpResponse response, InputStream in){
		try{
			if(in!=null){
		    	in.close();
		    }
		    if(response!=null){
		    	response.close();
		    }
		    if(httpclient!=null){
		    	httpclient.close();
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static byte[] readStream(InputStream in)throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len=in.read(buffer))!=-1){
			out.write(buffer, 0, len);
		}
		out.close();
		return out.toByteArray();
	}
	
	static InputStream doResponse(HttpResponse response)throws IOException{
		InputStream in = null;
		if(response.getStatusLine().getStatusCode()==200){
			HttpEntity entity = response.getEntity();
			if(entity!=null){
				in = entity.getContent();
			}
		}
		return in;
	}
	
	public static BufferedImage toImage(byte[] bs){
		ByteArrayInputStream in = new ByteArrayInputStream(bs);
		BufferedImage image = null;
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}
	
	public static void toImage(byte[] bs, String filename){
		ImageOutputStream iop = null;
		try {
			iop = new FileImageOutputStream(new File(filename));
			iop.write(bs, 0, bs.length);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(iop!=null) iop.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}