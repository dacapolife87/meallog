package meallog.meal.controller;

import java.util.List;

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
     
    /********************************************************
     * Web 게시글 출력
     * @param meal
     * @param session
     * @return Web -> List
     * @throws Exception
     ********************************************************/
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/meal/userMealList.do", method=RequestMethod.POST)
    public @ResponseBody  List userMealList(HttpSession session) throws Exception{
    	log.debug("[WebB] user Meal List ");
        List<Meal> list = mealService.selectUserMealList(session);
        return list;
    }
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/meal/shareMealList.do", method=RequestMethod.POST)
    public @ResponseBody List shareMealList(HttpSession session) throws Exception{
    	log.debug("[WebB] share Meal List ");
        List<Meal> list = mealService.selectShareMealList(session);
        return list;
    }
    /********************************************************
     * Mobile 게시글 출력
     * @param session
     * @return Mobile -> JSON[List]
     * @throws Exception
     ********************************************************/
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meal/userMealList.mobile", method=RequestMethod.POST)
    public @ResponseBody  JSONObject userMealListMobile(HttpSession session) throws Exception{
    	log.debug("[Mobile] user Meal List ");
	  	JSONObject resultJSON = new JSONObject();
        List<Meal> list = mealService.selectUserMealList(session);
        resultJSON.put("result", list);
        return resultJSON;
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meal/shareMealList.mobile", method=RequestMethod.POST)
    public @ResponseBody  JSONObject shareMealListMobile(HttpSession session) throws Exception{
    	log.debug("[Mobile] Share Meal List ");
	  	JSONObject resultJSON = new JSONObject();
        List<Meal> list = mealService.selectShareMealList(session);
        resultJSON.put("result", list); 
        return resultJSON;
    }
    
    /********************************************************
     *  Web // Mobile 해당 게시글 출력
     * @param requestMeal
     * @return Web ->  Meal // Mobile -> JSONObject
     * @throws Exception
     ********************************************************/
    
    @RequestMapping(value="/meal/userOneMealList.do", method=RequestMethod.POST)
    public @ResponseBody  Meal selectUserOneMealList(CommandMap requestMeal) throws Exception{
        Meal meal = mealService.selectUserOneMealList(requestMeal.getMap());
        return meal;
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meal/userOneMealList.mobile", method=RequestMethod.POST)
    public @ResponseBody  JSONObject selectUserOneMealListMobile(CommandMap requestMeal) throws Exception{
	  	JSONObject resultJSON = new JSONObject();
	  	Meal meal = mealService.selectUserOneMealList(requestMeal.getMap());
        resultJSON.put("result", meal);
        return resultJSON;
    }
    
      
    /********************************************************
     *  Web // Mobile 게시글  작성
     * @param meal		[Web] Map  // [Mobile] JSON
     * @param request
     * @param session
     * @return Web ->  ModelAndView // Mobile -> JSON[result_Code]
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/meal/mealUploadList.do")
    public ModelAndView insertMealBoard(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/meal/main.do");
        log.debug("[WebB] meal upload Request");
        log.debug("map data : "+meal.getMap());
        mealService.insertMeal(meal.getMap(),request,session);
        return mv;
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meal/mealUploadList.mobile", method=RequestMethod.POST)
    public @ResponseBody JSONObject insertMealBoardMoblie(CommandMap meal, HttpServletRequest request, HttpSession session) throws Exception{
    	log.debug("[Mobile] meal upload Request");
    	log.debug("map data : "+meal.getMap());
    	JSONObject resultJSON = new JSONObject();
    	try {
    		request.setCharacterEncoding("UTF-8");
    		mealService.insertMealMobile(meal.getMap(), request, session);
    		resultJSON.put("result", "insert_OK");
		} catch (Exception e) {
			// TODO: handle exception
			resultJSON.put("result", "insert_FAIL");
		}
    	return resultJSON;
    }
    /********************************************************
     * Web // Mobile 게시글 삭제
     * @param commandMap
     * @return Web ->  ModelAndView // Mobile -> JSON[result_Code]
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/meal/deleteUserMeal.do")
    public ModelAndView deleteMeal(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/meal/main.do");
         
        mealService.deleteMeal(commandMap.getMap());
         
        return mv;
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meal/deleteUserMeal.mobile")
    public @ResponseBody JSONObject deleteMealMobile(CommandMap commandMap) throws Exception{
    	JSONObject resultJSON = new JSONObject();
    	try {
    		mealService.deleteMeal(commandMap.getMap());
    		resultJSON.put("result", "DELETE_OK");
		} catch (Exception e) {
			// TODO: handle exception
			resultJSON.put("result", "DELETE_FAIL");
		}
    	return resultJSON;
    }
    /********************************************************
     * Web // Mobile 게시글 수정
     * @param commandMap
     * @return Web ->  ModelAndView // Mobile -> JSON[result_Code]
     * @throws Exception
     ********************************************************/
    @RequestMapping(value="/meal/updateUserMeal.do")
    public ModelAndView updateMeal(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/meal/main.do");
        mealService.updateMeal(commandMap.getMap());
        return mv;
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/meal/updateUserMeal.mobile")
    public @ResponseBody JSONObject updateMealMobile(CommandMap commandMap) throws Exception{
    	JSONObject resultJSON = new JSONObject();
    	try {
    		mealService.updateMeal(commandMap.getMap());
    		resultJSON.put("result", "UPDATE_OK");
		} catch (Exception e) {
			// TODO: handle exception
			resultJSON.put("result", "UPDATE_FAIL");
		}
    	return resultJSON;
    }
    /********************************************************
     *  test 용 함수
     *  작업할때 만들고 추후에 지울것
     * @param meal
     * @param request
     * @param session
     * @return
     * @throws Exception
     ********************************************************/
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
      /********************************************************
       * test 용 함수 끝
       ********************************************************/
}




