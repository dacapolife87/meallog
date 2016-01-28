package meallog.main.service;

import java.util.List;
import java.util.Map;

import meallog.meal.vo.Meal;

public interface MainService {

//	List<Meal> selectBoardList(Meal meal) throws Exception;
	void joinMember(Map<String, Object> map) throws Exception;
}

