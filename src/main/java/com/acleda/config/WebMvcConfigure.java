package com.acleda.config;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.acleda.*")
public class WebMvcConfigure implements WebMvcConfigurer {
	@Autowired ServletContext servletContext;

	@Bean
	ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setCacheable(false);
		templateResolver.setTemplateMode("HTML");
		templateResolver.setPrefix("/WEB-INF/views/");
		templateResolver.setSuffix(".html");

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setCharacterEncoding("UTF-8");

		return viewResolver;
	}
	
	@Bean
    public DataSource dataSource() {
        try {
            JndiObjectFactoryBean jndiFactory = new JndiObjectFactoryBean();
            jndiFactory.setJndiName("lrm");
            
            Properties jndiEnvironment = new Properties();
            jndiEnvironment.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
            jndiEnvironment.put("java.naming.provider.url", "t3://127.0.0.1:7001");
            jndiFactory.setJndiEnvironment(jndiEnvironment);
            
            jndiFactory.setProxyInterface(DataSource.class);
            jndiFactory.afterPropertiesSet();

            return (DataSource) jndiFactory.getObject();
        } catch (Exception e) {
            System.err.println("ERROR: Failed to lookup DataSource via JNDI.");
            e.printStackTrace();
            return null;
        }
    }
	   @Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	       registry.addResourceHandler("/resources/**")
	               .addResourceLocations("/resources/");
	   }

}



