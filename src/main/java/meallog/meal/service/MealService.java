package meallog.meal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import meallog.meal.vo.Meal;

public interface MealService {
	//List<Meal> selectBoardList(Meal meal) throws Exception;
	List<Meal> selectUserMealList(HttpSession session) throws Exception;
	List<Meal> selectShareMealList(HttpSession session) throws Exception;
	Meal selectUserOneMealList(Map<String, Object> map) throws Exception;
	void updateMeal(Map<String, Object> map) throws Exception;
	void deleteMeal(Map<String, Object> map) throws Exception;
	void insertMeal(Map<String, Object> map,HttpServletRequest request,HttpSession session) throws Exception;
	void insertMealMobile(Map<String, Object> map,HttpServletRequest request,HttpSession session) throws Exception;
	
	List<String> autocompleteMeal(Map<String, Object> map) throws Exception;
	
	//Test 22
	Meal selectPopupMeal(Map<String,Object> map) throws Exception;
}

