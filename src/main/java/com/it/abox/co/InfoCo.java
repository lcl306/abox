package com.it.abox.co;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.abox.set.SetUtil;
import com.it.abox.util.CoUtil;

/**
 * 所有的方法均返回@ResponseBody，可以使用@RestController
 * */
@RestController
public class InfoCo {
	
	@RequestMapping("/abox/info")
	public String info(HttpServletRequest request, HttpServletResponse response){
		return SetUtil.info();
	}
	
	/**
	 * 自动变为json数据
	 * */
	@RequestMapping("/abox/snapshotInfo")
	public InfoDto snapshotInfo(HttpServletRequest request, HttpServletResponse response){
		InfoDto dto = new InfoDto();
		dto.setIp(SetUtil.getUrl());
		System.out.println(dto.getIp());
		dto.setTemplate(SetUtil.template());
		return dto;
	}
	
	@RequestMapping("/abox/sw")
	public String sw(HttpServletRequest request, HttpServletResponse response){
		if(request.getParameter("switch").equals("true")){
			SetUtil.debug(true);
		}else{
			SetUtil.debug(false);
		}
		return "";
	}
	
	@RequestMapping("/abox/snapshotImage")
	public void snapshotImage(HttpServletRequest request, HttpServletResponse response){
		CoUtil.image(response, SetUtil.snapshot(), "jpg");
	}

}
