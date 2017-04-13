package com.it.abox.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.it.abox.set.SetUtil;

public class MyInterceptor implements HandlerInterceptor {
	
	public static final String URL_NAME = "ABOX_URL";

	/**
     * 页面渲染之后调用，一般用于资源清理操作
     */
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception exp)throws Exception {
		System.out.println("------afterCompletion");
	}

	/**
     * controller 执行之后，且页面渲染之前调用
     */
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,Object handler, ModelAndView mv) throws Exception {
		System.out.println("------postHandle");
	}

	/**
     * controller 执行之前调用
     */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		System.out.println("------preHandle");
		setUrl(req);
		CoUtil.cors(req,res);
		res.setCharacterEncoding(CoUtil.CODE);
		return true;
	}
	
	private void setUrl(HttpServletRequest req){
		String url = (String)req.getSession().getAttribute(URL_NAME);
		if(url==null){
			url = SetUtil.D_URL;
		}
		SetUtil.setUrl(url);
	}

}
