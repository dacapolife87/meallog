package meallog.meal.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import meallog.meal.service.MealService;
import meallog.meal.vo.Meal;

@Controller
public class mealController {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
    @Resource(name="mealService")
    private MealService mealService;
     

    
    
    /*
     * Web  // Mobile 게시글 출력
     * /meal/userMealList.do
     * /meal/shareMealList.do
     * /meal/userMealList.mobile
     * /meal/shareMealList.mobile
     * 개인과 공유 구분
     * Web ->  List 
     * Mobile -> JSON[List]
     * 반환
     * */
    @RequestMapping(value="/meal/userMealList.do", method=RequestMethod.POST)
    public @ResponseBody  List userMealList(CommandMap meal, HttpSession session) throws Exception{
    	log.debug("[WebB] user Meal List ");
        List<Meal> list = mealService.selectUserMealList(session);
        return list;
    }
    @RequestMapping(value="/meal/shareMealList.do", method=RequestMethod.POST)
    public @ResponseBody List shareMealList(CommandMap meal, HttpSession session) throws Exception{
    	log.debug("[WebB] share Meal List ");
        List<Meal> list = mealService.selectShareMealList(session);
        return list;
    }
    @RequestMapping(value="/meal/userMealList.mobile")
    public @ResponseBody  JSONObject userMealListMobile(HttpSession session) throws Exception{
    	log.debug("[Mobile] user Meal List ");
	  	JSONObject resultJSON = new JSONObject();
        List<Meal> list = mealService.selectUserMealList(session);
        resultJSON.put("result", list);
        return resultJSON;
    }
    @RequestMapping(value="/meal/shareMealList.mobile")
    public @ResponseBody  JSONObject shareMealListMobile(HttpSession session) throws Exception{
    	log.debug("[Mobile] Share Meal List ");
	  	JSONObject resultJSON = new JSONObject();
        List<Meal> list = mealService.selectShareMealList(session);
        resultJSON.put("result", list); 
        return resultJSON;
    } 
    /*
     * Web // Mobile 게시글  작성
     * /meal/mealBoardWrite.do
     * /meal/mealUploadList.mobile
     * Web ->  Map 으로 전달 받아  Service 의  insertMeal 에서 처리
     * Mobile -> JSON 으로 전달 받아  Service 의  insertMealMobile 에서  JSON을 추출후 처리
     * 반환
     * */
    @RequestMapping(value="/meal/mealBoardWrite.do")
    public ModelAndView insertMealBoard(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/meal/main.do");
        log.debug("[WebB] meal upload Request");
        log.debug("map data : "+meal.getMap());
        mealService.insertMeal(meal.getMap(),request,session);
        return mv;
    }
    @RequestMapping(value="/meal/mealUploadList.mobile", method=RequestMethod.POST)
    public void insertMealBoardMoblie(CommandMap meal, HttpServletRequest request, HttpSession session) throws Exception{
    	log.debug("[Mobile] meal upload Request");
    	log.debug("map data : "+meal.getMap());
    	request.setCharacterEncoding("UTF-8");
    	mealService.insertMealMobile(meal.getMap(), request, session);
    }
    
    
    
    @RequestMapping(value="/meal/test.do", method=RequestMethod.POST)
    public @ResponseBody List test(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        List<Meal> list = mealService.selectUserMealList(session);
        log.debug("userMealList");
        log.debug(list);
        return list;
    }
    @RequestMapping(value="/meal/test2.do", method=RequestMethod.POST)
    public @ResponseBody List test2(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        List<Meal> list = mealService.selectShareMealList(session);

        log.debug("userMealList");
        log.debug(list);
        return list;
    }
    
//    @RequestMapping(value="/meal/test3.do", method=RequestMethod.GET)
//    public ModelAndView modalTest(HttpSession session) throws Exception{
//    	log.debug("Modal Test ========================== Modal Test");
//    	ModelAndView mv = new ModelAndView("/main/modal");
//    	return mv;
//    }
    
//    @RequestMapping(value="/meal/test3.do", method=RequestMethod.GET)
//    public ModelAndView modalTest(HttpServletRequest request, HttpSession session) throws Exception{
//    	log.debug("Modal Test ========================== Modal Test");
//    	ModelAndView mv = new ModelAndView("/main/modal");
//    	return mv;
//    }
    
      @RequestMapping(value="/meal/test3.do", method=RequestMethod.GET)
      public String modalTest(HttpSession session) throws Exception{
    	  log.debug("/meal/test3.do Call");
    	 
    	  return "/main/modal";
      }
    
}




