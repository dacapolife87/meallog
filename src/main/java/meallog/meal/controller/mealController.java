package meallog.meal.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    
    @RequestMapping(value="/meal/mealBoardList.do")
    public ModelAndView openMealBoardList(Meal meal) throws Exception{
        ModelAndView mv = new ModelAndView("/meal/mealList");
        List<Meal> list = mealService.selectBoardList(meal);
        mv.addObject("list", list);
        return mv;
    }
    
    @RequestMapping(value="/meal/mealBoardWrite.do")
    public ModelAndView insertMealBoard(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/meal/main.do");
        
        mealService.insertBoard(meal.getMap(),request,session);
        
        return mv;
    }
    
    @RequestMapping(value="/meal/test.do", method=RequestMethod.POST)
    public @ResponseBody List test(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        String test = "test111";
        List<Meal> list = mealService.selectUserMealList(session);
        log.debug("mealController");
        return list;
    }
    
    
}




