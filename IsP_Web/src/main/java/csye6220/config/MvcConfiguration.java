package csye6220.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.sun.xml.bind.v2.runtime.unmarshaller.Intercepter;

import csye6220.interceptor.AdminAuthInterceptor;
import csye6220.interceptor.AuthInterceptor;
import csye6220.interceptor.SecurityInterceptor;

@Configuration
@ComponentScan(basePackages="csye6220")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	
	
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	


	
	@Bean
	public VelocityConfigurer velocityConfig()
	{
		VelocityConfigurer cfg= new VelocityConfigurer();
		cfg.setResourceLoaderPath("/");
		return cfg;
	}
	
	
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MultipartResolver multipartResolver(){
		return new CommonsMultipartResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new AuthInterceptor())
		        .addPathPatterns("/customer/**");
		
		registry.addInterceptor(new AdminAuthInterceptor())
				.addPathPatterns("/admin/**");
		
		registry.addInterceptor(new SecurityInterceptor())
				.addPathPatterns("/**");
		
	}
	
}
