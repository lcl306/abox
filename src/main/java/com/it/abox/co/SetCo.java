package com.it.abox.co;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.abox.util.MyInterceptor;

@RestController
public class SetCo {
	
	@RequestMapping("/abox/change")
	public String change(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		System.out.println(name);
		//SetUtil.change(name);
		return "";
	}
	
	@RequestMapping("/abox/set")
	public String set(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String attr = request.getParameter("attr");
		String value = request.getParameter("val");
		System.out.println(name+"."+attr+"="+value);
		//SetUtil.set(name, attr, value);
		return "";
	}
	
	@RequestMapping("/abox/connect")
	public String connect(HttpServletRequest request, HttpServletResponse response){
		String ip = request.getParameter("ip");
		String port = request.getParameter("port");
		request.getSession().setAttribute(MyInterceptor.URL_NAME, ip+":"+port);
		System.out.println(ip+":"+port);
		return "";
	}
	
	@RequestMapping("/abox/reload")
	public String reload(HttpServletRequest request, HttpServletResponse response){
		//SetUtil.reload();
		return "";
	}
	
	@RequestMapping("/abox/reboot")
	public String reboot(HttpServletRequest request, HttpServletResponse response){
		//SetUtil.reboot();
		return "";
	}

}
