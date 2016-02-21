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
     
    
    
    @RequestMapping(value="/meal/userMealList.do")
    public @ResponseBody  ModelAndView userMealList(HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Meal> list = mealService.selectUserMealList(session);
        mv.addObject("list", list);
        log.debug("userMealList");
        log.debug(list);
        
        return mv;
    }
    @RequestMapping(value="/meal/userMealList.mobile")
    public @ResponseBody  JSONObject userMealListMobile(HttpSession session) throws Exception{

	  	JSONObject resultJSON = new JSONObject();
	  	
        List<Meal> list = mealService.selectUserMealList(session);
        
        resultJSON.put("result", list);
       
        log.debug("userMealListMobile");
        
        return resultJSON;
    }
    @RequestMapping(value="/meal/shareMealList.mobile")
    public @ResponseBody  JSONObject shareMealListMobile(HttpSession session) throws Exception{

	  	JSONObject resultJSON = new JSONObject();
	  	
        List<Meal> list = mealService.selectShareMealList(session);
        
        resultJSON.put("result", list);
       
        log.debug("shareMealListMobile");
        
        return resultJSON;
    }
    
    
    
    @RequestMapping(value="/meal/mealBoardList.do")
    public ModelAndView openMealBoardList(Meal meal) throws Exception{
        ModelAndView mv = new ModelAndView("/meal/mealList");
        List<Meal> list = mealService.selectBoardList(meal);
        mv.addObject("list", list);
        return mv;
    }
    
    //중민이 파일 업로드
    @RequestMapping(value="/meal/mealUploadList.mobile", method=RequestMethod.POST)
    public void insertMealBoardMoblie(CommandMap meal, HttpServletRequest request, HttpSession session) throws Exception{
    	log.debug("meal upload : ");
    	request.setCharacterEncoding("UTF-8");
    	mealService.insertMealMobile(meal.getMap(), request, session);
    	mealService.insertBoard(meal.getMap(), request, session);
    }
    
    @RequestMapping(value="/meal/mealBoardWrite.do")
    public ModelAndView insertMealBoard(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/meal/main.do");
        
        mealService.insertBoard(meal.getMap(),request,session);
        
        return mv;
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
    
}




