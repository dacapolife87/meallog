package meallog.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @RequestMapping(value="/meallogin.mobile", method=RequestMethod.POST)
    public @ResponseBody JSONObject mealLoginMobile(CommandMap commandMap,HttpSession session,HttpServletRequest request,HttpServletResponse response ) throws Exception{
	  	String result;
	  	JSONObject resultJSON = new JSONObject();
	  	log.debug("Connect mobile");
    	Member member = userService.loginMember(commandMap.getMap());

	  	if(member != null){
	  		log.debug("login succeed");
	  		session.setAttribute("member",member);
	  		result = "LOGIN_OK";
	  		
	  		
	  		

	  	}else{
	  		log.debug("login fail");
	  		result = "LOGIN_FAIL";
	  	}
	  	resultJSON.put("STATUS", result);
	  	//return result;
		return resultJSON;
	  }
    
	  @RequestMapping(value="/meallogin.do")
	  public String mealLogin(CommandMap commandMap,HttpSession session,HttpServletRequest request ) throws Exception{
	  	Member member = userService.loginMember(commandMap.getMap());
	  	String returnValue;
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
	  		//mv = new ModelAndView("redirect:/logSucceed.do");
	  		returnValue = "redirect:/logSucceed.do";
	  		log.debug("login succeed");
	  		session.setAttribute("member",member);

	  	}else{
	  		//mv = new ModelAndView("redirect:/logFail.do");
	  		returnValue = "redirect:/logFail.do";
	  		log.debug("login fail");
	  	}
	  	
	      return returnValue;
	  }
    
    @RequestMapping(value="/meal/main.do")
    public ModelAndView mealMain(CommandMap commandMap,HttpSession session) throws Exception{
    	ModelAndView mv = new ModelAndView("/main/mainpage");
    	
    	log.debug("login after");
    	log.debug("login : "+session);
        log.debug("login : "+session.getValue("member"));
        return mv;
    }
    @RequestMapping(value="/meal/logout.do")
    public ModelAndView mealLogout(CommandMap commandMap,HttpSession session,HttpServletResponse                 
    		   response,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("/main/logOut");
    	
    	final String refererUrl = request.getHeader("Referer");
    	response.setHeader(refererUrl, "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        session.removeAttribute("member");
    	log.debug("logout after");
    	log.debug("logout : "+session);
        log.debug("logout : "+session.getValue("member"));
    	session.invalidate();
        return mv;
    }
    
    @RequestMapping(value="/logSucceed.do")
    public ModelAndView mealLoginSucced(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/main/logInSucceed");
    	log.debug("login Succeed Func");
        return mv;
    }
    @RequestMapping(value="/logFail.do")
    public ModelAndView mealLoginFail(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/main/logInfail");
    	log.debug("login fail Func");
        return mv;
    }
}
