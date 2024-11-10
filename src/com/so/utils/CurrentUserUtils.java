package com.so.utils;

import javax.servlet.http.HttpServletRequest;

import com.so.system.bean.User;

public class CurrentUserUtils {
	
	/**
	 * 获取当前登录用户信息
	 * @param request
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request){
		User user = null;
		Object attribute = request.getSession().getAttribute("login");
		if (attribute!=null && attribute instanceof User) {
			user = (User) attribute;
		}
		return user;
	}
}
