package com.hanbo.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hanbo.cms.comon.ConstClass;
import com.hanbo.cms.entity.User;

public class AuthIntercepter implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		
		//session 中获取存在用户
		User loginUser = (User)request.getSession().getAttribute(
				ConstClass.SESSION_USER_KEY);
		//不存在  则不放行
		if(loginUser==null) {
			// 跳转到登录页面，这里也可以使用重定向 
			request.getRequestDispatcher("/user/login").
				forward(request, response);
			return false;
		}
		//放行
		return true;
	}

}
