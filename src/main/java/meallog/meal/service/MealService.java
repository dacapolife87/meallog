package meallog.meal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import meallog.meal.vo.Meal;

public interface MealService {

	List<Meal> selectBoardList(Meal meal) throws Exception;
	List<Meal> selectUserMealList(HttpSession session) throws Exception;
	void insertBoard(Map<String, Object> map,HttpServletRequest request,HttpSession session) throws Exception;
}

