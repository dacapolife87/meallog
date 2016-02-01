package meallog.meal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import meallog.meal.vo.Meal;

public interface MealService {

	List<Meal> selectBoardList(Meal meal) throws Exception;
	void insertBoard(Map<String, Object> map,HttpServletRequest request) throws Exception;
}

