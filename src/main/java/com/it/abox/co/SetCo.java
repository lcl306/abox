package com.it.abox.co;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.abox.config.AppConfig;
import com.it.abox.config.Context;
import com.it.abox.util.MyInterceptor;

@RestController
@RequestMapping("/abox")
public class SetCo {
	
	@Resource
	private Context context;
	
	@Resource
	private AppConfig appConfig;
	
	@RequestMapping("/change")
	public String change(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		System.out.println(name);
		System.out.println(context.getDb().getUsername());
		System.out.println(appConfig.getName());
		//SetUtil.change(name);
		return "";
	}
	
	@RequestMapping("/setting")
	public String set(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String attr = request.getParameter("attr");
		String value = request.getParameter("val");
		System.out.println(name+"."+attr+"="+value);
		//SetUtil.set(name, attr, value);
		return "";
	}
	
	@RequestMapping("/connect")
	public String connect(HttpServletRequest request, HttpServletResponse response){
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		request.getSession().setAttribute(MyInterceptor.URL_NAME, ip+":"+port);
		System.out.println(ip+":"+port);
		return "";
	}
	
	@RequestMapping("/reload")
	public String reload(HttpServletRequest request, HttpServletResponse response){
		//SetUtil.reload();
		return "";
	}
	
	@RequestMapping("/reboot")
	public String reboot(HttpServletRequest request, HttpServletResponse response){
		//SetUtil.reboot();
		return "";
	}

}
