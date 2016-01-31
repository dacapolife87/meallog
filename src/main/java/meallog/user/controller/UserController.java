package meallog.user.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
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
    
//    @RequestMapping(value="/meallog.do")
//    public String checkMember(CommandMap commandMap,HttpSession session) throws Exception{
//    	Member member = userService.loginMember(commandMap.getMap());
//
//    	if(member != null){
//    		
//    		log.debug("login succeed");
//    		session.setAttribute("member",member);
//    		return "redirect:/mealmain.do";
//    	}else{
//    		log.debug("login fail");
//    		return "redirect:/main.do";
//    	}
//    }
    
	  @RequestMapping(value="/meallog.do")
	  public ModelAndView checkMember(CommandMap commandMap,HttpSession session) throws Exception{
	  	Member member = userService.loginMember(commandMap.getMap());
	  	ModelAndView mv;
	  	if(member != null){
	  		mv = new ModelAndView("redirect:/mealmain.do");
	  		log.debug("login succeed");
	  		session.setAttribute("member",member);
	  	}else{
	  		mv = new ModelAndView("redirect:/main.do");
	  		log.debug("login fail");
	  	}
	  	
	      return mv;
	  }
    
//    @RequestMapping(value="/meallog.do")
//    public ModelAndView checkMember(CommandMap commandMap,HttpSession session) throws Exception{
//    	Member member = userService.loginMember(commandMap.getMap());
//    	ModelAndView mv;
//    	if(member != null){
//    		mv = new ModelAndView("redirect:/mealmain.do");
//    		log.debug("login succeed");
//    		session.setAttribute("member",member);
//    	}else{
//    		mv = new ModelAndView("redirect:/main.do");
//    		log.debug("login fail");
//    	}
//    	
//        return mv;
//    }
	  
    
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
}
