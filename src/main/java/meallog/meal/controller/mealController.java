package meallog.meal.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import meallog.common.common.CommandMap;
import meallog.meal.service.MealService;
import meallog.meal.vo.Meal;

@Controller
public class mealController {
    @Resource(name="mealService")
    private MealService mealService;
     
    @RequestMapping(value="/meal/mealBoardList.do")
    public ModelAndView openMealBoardList(Meal meal) throws Exception{
        ModelAndView mv = new ModelAndView("/meal/mealList");
        List<Meal> list = mealService.selectBoardList(meal);
        mv.addObject("list", list);
        return mv;
    }
    
    @RequestMapping(value="/meal/mealBoardWrite.do")
    public ModelAndView insertMealBoard(CommandMap meal, HttpServletRequest request,HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/mealmain.do");
        
        mealService.insertBoard(meal.getMap(),request,session);
        
        return mv;
    }
    
    
}




