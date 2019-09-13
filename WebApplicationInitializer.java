package com.virtusa.banking.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer 
      extends AbstractAnnotationConfigDispatcherServletInitializer {

  // Load database and spring security configuration
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { AppConfig.class, WebSecurityConfig.class };
  }

  // Load database and spring web configuration
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebMVCConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}
