package meallog.meal.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import meallog.meal.service.MealService;
import meallog.meal.vo.Meal;

@Controller
public class mealController {
    @Resource(name="mealService")
    private MealService mealService;
     
    @RequestMapping(value="/meal/mealBoardList.do")
    public ModelAndView openMealBoardList(Meal commandMeal) throws Exception{
        ModelAndView mv = new ModelAndView("/meal/mealList");
        List<Meal> list = mealService.selectBoardList(commandMeal);
        mv.addObject("list", list);
        return mv;
    }
}




