package csye6220.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter  {


	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	
		
		HttpSession session= request.getSession();
		if(session.getAttribute("Authenticated")!="true")
		  {
			response.sendRedirect("/SpringApplication/loginPage");
			return false;
		  }
		
		return true;
		
			
	}
	
	
}
