package csye6220.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Map map=request.getParameterMap();
		Iterator iterator =map.entrySet().iterator();
		
		Pattern crossSiteScriptingPattern =Pattern.compile("((\\%3C)|<)((\\%2F)|\\/)*[a-z0-9\\%]+((\\%3E)|>)");
		
		Pattern sqlInjectionpattern =Pattern.compile("((\\%3D)|(=))[^\\n]*((\\%27)|(\\')|(\\-\\-)|(\\%3B)|(;))");
		
		
		boolean detected=false;
		
		while(iterator.hasNext())
		{
			Map.Entry<String,String[]> entry=(Map.Entry<String, String[]>)iterator.next();
			
			if(entry.getValue().length>1)
			{
				for(String str:entry.getValue())
				{
					if(crossSiteScriptingPattern.matcher(str).find()||sqlInjectionpattern.matcher(str).find())
					{
						detected=true;
						
					}
				}
				
			}
			else
			{
				String str=entry.getValue()[0];
				if(crossSiteScriptingPattern.matcher(str).find()||sqlInjectionpattern.matcher(str).find())
				{
					detected=true;
				}
				
			}
			
			if (detected) break;
		}
		
		
		if(detected)
		{
		   System.out.println("Detected potential attack");
		   response.sendError(HttpServletResponse.SC_FORBIDDEN,"Inappropriate parameter is detected");
		   return false;
		}
				
	    return true;
		
			
	}
	
	
	
	

}
