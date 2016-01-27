package meallog.meal.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import meallog.meal.dao.MealDAO;
import meallog.meal.vo.Meal;

@Service("mealService")
public class MealServiceImpl implements MealService{
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="mealDAO")
    private MealDAO mealDAO;

	@Override
	public List<Meal> selectBoardList(Meal meal) throws Exception {
		// TODO Auto-generated method stub
		return mealDAO.selectBoardList(meal);
	}
 
}
