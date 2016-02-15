package meallog.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import meallog.common.logger.LoggerInterceptor;

public class SessionCheck extends HandlerInterceptorAdapter{
    protected Log log = LogFactory.getLog(LoggerInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	try {
          
            if(request.getSession().getAttribute("member") == null ){
                    response.sendRedirect("/meallog/main.do"); 
                   
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return true;

    }
}
