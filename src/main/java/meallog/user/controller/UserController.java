package meallog.user.controller;

import javax.annotation.Resource;
import javax.security.sasl.AuthorizeCallback;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.event.AuthorizedEvent;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import meallog.common.common.CommandMap;
import meallog.common.dao.AbstractDAO;
import meallog.user.service.UserService;
import meallog.user.vo.Member;

@Controller
public class UserController {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
    
	@Resource(name="userService")
    private UserService userService;
     
    @RequestMapping(value="/joinMember.do")
    public ModelAndView insertBoard(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/main.do");
        userService.joinMember(commandMap.getMap());

        
        return mv;
    }
    
    
	  @RequestMapping(value="/meallog.do")
	  public ModelAndView mealLogin(CommandMap commandMap,HttpSession session,HttpServletRequest request ) throws Exception{
	  	Member member = userService.loginMember(commandMap.getMap());
//	  	Base64 base;
//	  	String auth1;
//	  	byte[] auth2;
//	  	log.debug("login data check");
//	  	log.debug("login data11 : "+request.getHeader("Authorization"));
//	  	
//	  	
//	  	auth1 = request.getHeader("Authorization");
//	  	auth2 = Base64.decode(auth1.getBytes());
//	  	
//	  	String byteToString = new String(auth2,0,auth2.length);
//	  	
//	  	log.debug("byteToString : "+byteToString);
	  	
//	 // The token is 'valid' so magically get a user id from it 
//	  	 Long id = getUserIdFromToken(auth); 
//	  	         
//	  	 // Create our Authentication and let Spring know about it 
//	  	Authentication auth = new DemoAuthenticationToken(id); 
//	  	SecurityContextHolder.getContext().setAuthentication(auth);             

//	  	
//	  	log.debug("login data2 : "+commandMap.getMap());
	  	ModelAndView mv;
	  	if(member != null){
	  		mv = new ModelAndView("redirect:/mealmain.do");
	  		log.debug("login succeed");
	  		session.setAttribute("member",member);
	  	}else{
	  		mv = new ModelAndView("redirect:/meallogFail.do");
	  		log.debug("login fail");
	  	}
	  	
	      return mv;
	  }
    
    @RequestMapping(value="/mealmain.do")
    public ModelAndView mealMain(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/main/mainpage");
    	log.debug("login after");
        return mv;
    }
    @RequestMapping(value="/meallogout.do")
    public ModelAndView mealLogout(CommandMap commandMap,HttpSession session) throws Exception{
    	ModelAndView mv = new ModelAndView("redirect:/main.do");
    	session.invalidate();
    	log.debug("logout after");
        return mv;
    }
    @RequestMapping(value="/meallogFail.do")
    public ModelAndView mealLoginFail(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/main/logInfail");
    	log.debug("login fail");
        return mv;
    }
}
