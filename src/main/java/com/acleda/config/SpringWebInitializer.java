package com.acleda.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.acleda.security.SecurityConfig;

public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	public SpringWebInitializer() {
		super();
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebMvcConfigure.class , SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfigure.class };
	}

	@Override
	protected String[] getServletMappings() {		
		return new String[] {"/"};
	}	
}

