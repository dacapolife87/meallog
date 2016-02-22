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
	
	/********************************************************
	 * mealLog 메인 
	 * @param session
	 * @return ModelAndView -> session 유무에 따른 페이지 이동
	 * @throws Exception
	 ********************************************************/
	@RequestMapping(value="/main.do")
    public ModelAndView introMeallog(HttpSession session) throws Exception{
        ModelAndView mv;
        if(session.getAttribute("member")!=null){
        	mv = new ModelAndView("redirect:/meal/main.do");
        }else{
        	mv = new ModelAndView("/main/meallogin");
        }
        return mv;
    }
	
	/********************************************************
	 * 회원가입
	 * @param commandMap
	 * @return Web ->  ModelAndView // Mobile -> JSON[result_Code]
	 * @throws Exception
	 ********************************************************/
    @RequestMapping(value="/joinMember.do", method=RequestMethod.POST)
    public ModelAndView insertBoard(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/main.do");
        userService.joinMember(commandMap.getMap());
        return mv;
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/joinMember.mobile", method=RequestMethod.POST)
    public @ResponseBody JSONObject joinMobile(CommandMap commandMap) throws Exception{
		JSONObject resultJSON = new JSONObject();
    	try {
    		userService.joinMember(commandMap.getMap());
    		resultJSON.put("result", "JOIN_OK");
		} catch (Exception e) {
			// TODO: handle exception
			resultJSON.put("result", "JOIN_FAIL");
		}
    	return resultJSON;
    }
    /********************************************************
     * ID 중복확인
     * @param commandMap
     * @return boolean 존재하면 true 없으면 false
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/idCheck.do", method=RequestMethod.POST)
    public @ResponseBody boolean joinIdCheck(CommandMap commandMap) throws Exception{
    	log.debug(userService.idCheck(commandMap.getMap()));
        return userService.idCheck(commandMap.getMap());
    }
    /********************************************************
     * Web // Mobile 로그인
     * @param commandMap
     * @param session
     * @return Web ->  String // Mobile -> JSON[result_Code]
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/meallogin.do", method=RequestMethod.POST)
    public String mealLogin(CommandMap commandMap,HttpSession session) throws Exception{
    	Member member = userService.loginMember(commandMap.getMap());
	  	String returnValue;

	  	if(member != null){
	  		returnValue = "redirect:/logSucceed.do";
	  		session.setAttribute("member",member);
	  	}else{
	  		returnValue = "redirect:/logFail.do";
	  	}  	
	    return returnValue;
	}
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meallogin.mobile", method=RequestMethod.POST)
    public @ResponseBody JSONObject mealLoginMobile(CommandMap commandMap,HttpSession session) throws Exception{
	  	String result;
	  	JSONObject resultJSON = new JSONObject();
	  	log.debug("Connect mobile");
    	Member member = userService.loginMember(commandMap.getMap());

	  	if(member != null){
	  		session.setAttribute("member",member);
	  		result = "LOGIN_OK";
	  	}else{
	  		result = "LOGIN_FAIL";
	  	}
	  	resultJSON.put("result", result);
		return resultJSON;
	}
    
    /********************************************************
     * 로그인후 처리
     * @param commandMap
     * @param session
     * @return Web ->  ModelAndView
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/meal/main.do")
    public ModelAndView mealMain() throws Exception{
    	ModelAndView mv = new ModelAndView("/main/mainpage");
        return mv;
    }
    @RequestMapping(value="/logSucceed.do")
    public ModelAndView mealLoginSucced() throws Exception{
    	ModelAndView mv = new ModelAndView("/login/logInSucceed");
        return mv;
    }
    @RequestMapping(value="/logFail.do")
    public ModelAndView mealLoginFail() throws Exception{
    	ModelAndView mv = new ModelAndView("/login/logInfail");
        return mv;
    }
    /********************************************************
     * 로그아웃 처리
     * @param session
     * @param response
     * @param request
     * @return Web ->  ModelAndView
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/meal/logout.do")
    public ModelAndView mealLogout(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
    	ModelAndView mv = new ModelAndView("/login/logOut");
    	final String refererUrl = request.getHeader("Referer");
    	
    	response.setHeader(refererUrl, "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        session.removeAttribute("member");
    	session.invalidate();
    	
        return mv;
    }
    
    
}
