package com.apress.prospringmvc.bookstore.web;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.apress.prospringmvc.bookstore.config.InfrastructureContextConfiguration;
import com.apress.prospringmvc.bookstore.config.TestDataContextConfiguration;
import com.apress.prospringmvc.bookstore.web.config.WebMvcContextConfiguration;

public class BookstoreWebApplicationInitializer implements WebApplicationInitializer {

	private static final Logger logger = LoggerFactory.getLogger(BookstoreWebApplicationInitializer.class);
	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	
	private static final long MAX_FILE_UPLOAD_SIZE = 1024 * 1024 * 5;
	private static final int FILE_SIZE_THRESHOD = 1024 * 1024;
	private static final long MAX_REQUEST_SIZE = -1L;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerListener(servletContext);
		registerDispatcherServlet(servletContext) ;
		registerHiddenHttpMethodFilter(servletContext);
	}
	
	private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
		// TODO Auto-generated method stub
		FilterRegistration.Dynamic registeration;
		registeration = servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
		registeration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, DISPATCHER_SERVLET_NAME);
	}

	private void registerListener(final ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = createContext(InfrastructureContextConfiguration.class, TestDataContextConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}
	
	private void registerDispatcherServlet(final ServletContext servletContext) {
		WebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		dispatcher.setMultipartConfig(new MultipartConfigElement("/home/chotom", MAX_FILE_UPLOAD_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOD));
	}

	private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(annotatedClasses);
		return context;
	}
	
}
