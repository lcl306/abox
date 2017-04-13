package com.it.abox.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CoUtil {
	
	public static final String CODE = "UTF-8";
	
	public static List<String> allowDomains = new ArrayList<>();
	
	//真正的产品不会用*，会使用有限的域名或IP
	static{
		allowDomains.add("*");
	}
	
	/**
	 * 跨域设置
	 * */
	public static void cors(HttpServletRequest request, HttpServletResponse response){
		if(allowDomains.contains("*")){
			response.setHeader("Access-Control-Allow-Origin", "*");
		}else if(allowDomains.contains(request.getHeader("origin"))){
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
			response.setHeader("Access-Control-Allow-Methods", "GET");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		}
	}
	
	/**
	 * 返回一个图片
	 * @param imageType: png jpg gif等
	 * */
	public static void image(HttpServletResponse response, BufferedImage image, String imageType){
		response.setContentType("image/"+imageType);
		try {
			ImageIO.write(image, imageType, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用于获得多个上传文件
	 * postName 是<input type="file" name="file1" />这里的name值，即file1
	 * */
	public static List<MultipartFile> getFiles(HttpServletRequest request, String postName){
		return ((MultipartHttpServletRequest)request).getFiles(encode(postName));
	}
	
	/**
	 * 用于获得一个上传文件
	 * postName 是<input type="file" name="file1" />这里的name值，即file1
	 * */
	public static MultipartFile getFile(HttpServletRequest request, String postName){
		return ((MultipartHttpServletRequest)request).getFile(encode(postName));
	}
	
	public static void pipe(MultipartFile file, OutputStream os){
		if(!file.isEmpty()){
			BufferedOutputStream out = null;
			InputStream in = null;
			try{
				out = new BufferedOutputStream(os);
				in = file.getInputStream();
				byte flush[]  = new byte[1024];  
		        int len = 0;  
		        while(0<=(len=in.read(flush))){  
		            out.write(flush, 0, len);  
		        }  
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					out.close();
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String decode(String str){
		try {
			return URLDecoder.decode(str, CODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
	}
	
	public static String encode(String str){
		try {
			return URLEncoder.encode(str, CODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
	}

}
