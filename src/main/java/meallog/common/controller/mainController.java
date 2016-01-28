package meallog.common.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import meallog.meal.service.MealService;
import meallog.meal.vo.Meal;

public class mainController {
	@Resource(name="meainService")
    private MainService mainService;
     
    @RequestMapping(value="/main.do")
    public ModelAndView introMeallog() throws Exception{
        ModelAndView mv = new ModelAndView("/home/meallogin.html");
        return mv;
    }
}
