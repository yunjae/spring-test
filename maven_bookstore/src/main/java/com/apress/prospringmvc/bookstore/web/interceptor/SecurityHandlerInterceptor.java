package com.apress.prospringmvc.bookstore.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.service.AuthenticationException;
import com.apress.prospringmvc.bookstore.web.controller.LoginController;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Account account = (Account)WebUtils.getSessionAttribute(request,LoginController.ACCOUNT_ATTRIBUTE);
		if (account == null) {
			// 이전 URL을 가져오고 저장한다.
			String url = request.getRequestURI().toString();
			WebUtils.setSessionAttribute(request, LoginController.REQUESTED_URL, url);
			throw new AuthenticationException("Authentication required", "authentication.required");
		}
		return true;
	}
}
