package csye6220.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AdminAuthInterceptor extends HandlerInterceptorAdapter  {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	
		
		HttpSession session= request.getSession();
		if(session.getAttribute("AdminUser")!="true")
		  {
			response.sendRedirect("/SpringApplication/loginPage");
			return false;
		  }
		
		return true;
		
			
	}
}
