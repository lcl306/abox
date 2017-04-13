package com.it.abox.set;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.abox.util.HttpClientUtil;

public class SetUtil {

	//直连的url=192.168.1.99 port=8080 ，gateway=172.16.0.1
	//计算机本地连接的ip=192.168.1.2 netmask=255.255.255.0 gateway=192.168.129.1 DNS=192.168.129.1
	// NTP服务器，如果是外网：time.windows.com
	public static String D_URL = "192.168.129.207:8080";
	
	static ThreadLocal<String> urls = new ThreadLocal<>();
	
	public static String getUrl(){
		return urls.get();
	}
	
	public static void setUrl(String url){
		urls.set(url);
	}
	
	public static void set(String comp, String attr, String val){
		String url = "";
		try {
			url = "http://"+getUrl()+"/set?"+comp+"."+attr+"="+URLEncoder.encode(val, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] bs = HttpClientUtil.get(url);
		System.out.println(new String(bs));
	}
	
	public static void reload(){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/reload");
		System.out.println(new String(bs));
	}
	
	public static void change(String name){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/change?name="+name);
		System.out.println(new String(bs));
	}
	
	public static String template(){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/template");
		String tem = new String(bs);
		System.out.println(tem);
		return tem;
	}
	
	public static String info(){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/info");
		return new String(bs);
	}
	
	public static void reboot(){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/reboot");
		System.out.println(new String(bs));
	}
	
	public static void debug(boolean on){
		String i = on?"on":"off";
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/debug?sw="+i);
		System.out.println(new String(bs));
	}
	
	public static String snapshot(String path){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/snapshot");
		String filename = path+"/"+Thread.currentThread().getName()+"_snapshot.jpg";
		HttpClientUtil.toImage(bs, filename);
		return filename;
	}
	
	public static BufferedImage snapshot(){
		byte[] bs = HttpClientUtil.get("http://"+getUrl()+"/snapshot");
		return HttpClientUtil.toImage(bs);
	}
	
	public static void upload(List<String> filenames){
		Map<String, String> maps = new HashMap<String, String>();  
        //maps.put("username", "test01");  
        //maps.put("password", "123456");  
        List<File> fileLists = new ArrayList<File>();
        for(String filename : filenames){
        	 fileLists.add(new File(filename));  
        } 
        byte[] bs = HttpClientUtil.sendHttpPost("http://"+getUrl()+"/upload", maps, fileLists);
        System.out.println(new String(bs));
	}
	
	public static void changeTemplate(String name){
		SetUtil.change(name);
		SetUtil.reload();
		SetUtil.template();
	}
	
	public static void main(String[] args){
		//reboot();
		//set("ok", "action", "http://192.168.1.99:8080/template");
		/*List<String> filenames = new ArrayList<>();
		filenames.add("C:\\tools\\TemplateEdit\\TempFilesBK\\template127.xml");
		upload(filenames);*/
		reload();
	}
}
