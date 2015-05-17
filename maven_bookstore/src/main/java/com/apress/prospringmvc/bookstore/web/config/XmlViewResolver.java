package com.apress.prospringmvc.bookstore.web.config;

import java.util.Locale;

import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

public class XmlViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MarshallingView view = new MarshallingView();
		view.setMarshaller(new XStreamMarshaller());
		view.setContentType("application/xml;charset=UTF-8");
		view.setModelKey("xmlData");
		return view;
	}
}
