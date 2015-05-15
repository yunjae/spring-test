package com.apress.prospringmvc.bookstore.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.apress.prospringmvc.bookstore.service.BookstoreService;

public class CommonDataInterceptor implements WebRequestInterceptor {
	@Autowired
	private BookstoreService bookstoreService;
		
	@Override
	public void preHandle(WebRequest arg0) throws Exception {		
	}
	
	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		if (model != null) {
			model.addAttribute("randomBooks", this.bookstoreService.findRandomBooks());
		}
	}
	
	@Override
	public void afterCompletion(WebRequest arg0, Exception arg1) throws Exception {}
}
