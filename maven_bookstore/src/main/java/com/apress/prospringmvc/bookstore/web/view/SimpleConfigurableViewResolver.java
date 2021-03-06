package com.apress.prospringmvc.bookstore.web.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class SimpleConfigurableViewResolver implements ViewResolver {

	private Map<String, ? extends View> views= new HashMap<String, View>();
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		return this.views.get(viewName);
	}
	
	public void setViews(Map<String, ? extends View> views) {
		this.views = views;
	}

}
