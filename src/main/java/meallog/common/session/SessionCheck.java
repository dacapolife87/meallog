package meallog.common.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import meallog.common.logger.LoggerInterceptor;
import meallog.user.vo.Member;

public class SessionCheck extends HandlerInterceptorAdapter{
    protected Log log = LogFactory.getLog(LoggerInterceptor.class);
    private Member member;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	try {
            if(request.getSession().getAttribute("member") != null ){
            	return true;
            }else if(request.getSession().getAttribute("kakao") != null ){
            	return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    	response.sendRedirect("/meallog/main.do"); 
        
        return false;

    }
}
