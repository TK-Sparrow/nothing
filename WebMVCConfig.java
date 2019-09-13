package com.virtusa.banking.configurations;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackages={"com.virtusa.banking.*"})
@EnableWebMvc
public class WebMVCConfig implements WebMvcConfigurer{

	@Autowired
	   private ApplicationContext applicationContext;	
	/*
	    * STEP 1 - Create SpringResourceTemplateResolver
	    * */
	   @Bean
	   public SpringResourceTemplateResolver templateResolver() {
	      SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	      templateResolver.setApplicationContext(applicationContext);
	      templateResolver.setPrefix("/views/");
	      templateResolver.setSuffix(".html");
	      return templateResolver;
	   }

	   /*
	    * STEP 2 - Create SpringTemplateEngine
	    * */
	   @Bean
	   public SpringTemplateEngine templateEngine() {
	      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	      templateEngine.setTemplateResolver(templateResolver());
	      templateEngine.setEnableSpringELCompiler(true);
	      return templateEngine;
	   }

	   /*
	    * STEP 3 - Register ThymeleafViewResolver
	    * */
	   @Override
	   public void configureViewResolvers(ViewResolverRegistry registry) {
	      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
	      resolver.setTemplateEngine(templateEngine());
	      registry.viewResolver(resolver);
	   }

	
	
	
	
	
	  @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  // TODO Auto-generated method stub
	  //WebMvcConfigurer.super.addResourceHandlers(registry); // Register resource
	  //handler for CSS and JS
	  registry.addResourceHandler("/styles/**").addResourceLocations("/styles/")
	  .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	  
	  // Register resource handler for CSS and JS
	  registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/")
	  .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic()); //
	  //Register resource handler for images
	  registry.addResourceHandler("/images/**").addResourceLocations("/images/")
	  .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic()); }
	  
	/*
	 * @Override public void configureViewResolvers(ViewResolverRegistry registry) {
	 * // TODO Auto-generated method stub
	 * //WebMvcConfigurer.super.configureViewResolvers(registry);
	 * registry.jsp("/views/", ".jsp"); }
	 * 
	 * @Override public void addInterceptors(InterceptorRegistry registry) { // TODO
	 * Auto-generated method stub WebMvcConfigurer.super.addInterceptors(registry);
	 * 
	 * }
	 */
	
}
