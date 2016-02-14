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
            //admin이라는 세션key를 가진 정보가 널일경우 로그인페이지로 이동
            if(request.getSession().getAttribute("member") == null ){
                    response.sendRedirect("/meallog/main.do"); 
                    log.debug("test");
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //admin 세션key 존재시 main 페이지 이동
        return true;

    }
}
