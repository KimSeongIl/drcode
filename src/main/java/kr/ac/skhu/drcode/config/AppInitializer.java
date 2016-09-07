package kr.ac.skhu.drcode.config;

import javax.servlet.ServletRegistration.Dynamic;

import kr.ac.skhu.drcode.security.Securityconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { Securityconfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
	    registration.setInitParameter("dispatchOptionsRequest", "true");
	}
	
}
