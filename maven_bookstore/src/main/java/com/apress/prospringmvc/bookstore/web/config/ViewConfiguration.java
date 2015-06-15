package com.apress.prospringmvc.bookstore.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

@Configuration
public class ViewConfiguration {
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();		
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();		
		List<View> defaultViews = new ArrayList<View>();
		
		viewResolvers.add(new JsonViewResolver());
		viewResolvers.add(tilesViewResolver());
		
		//defaultViews.add(jsonOrderView());
		//defaultViews.add(xmlOrderView());
		//viewResolver.setDefaultViews(defaultViews);
		viewResolver.setViewResolvers(viewResolvers);
		return viewResolver;
	}
	/*
	@Bean
	public MappingJackson2JsonView jsonOrderView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setModelKey("order");
		return jsonView;
	}
	
	@Bean 
	public MarshallingView xmlOrderView() {
		MarshallingView xmlView = new MarshallingView(marshaller());
		xmlView.setModelKey("order");
		return xmlView;
	}
	
	@Bean
	public Marshaller marshaller() {
		return new XStreamMarshaller();
	}
	*/

	@Bean
    public TilesConfigurer tilesConfigurer() {
        return new TilesConfigurer();
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setOrder(2);
        return tilesViewResolver;
    }
}
