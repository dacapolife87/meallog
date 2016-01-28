
package meallog.meal.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import meallog.common.dao.AbstractDAO;
import meallog.meal.vo.Meal;

@Repository("mealDAO")
public class MealDAO extends AbstractDAO{
    @SuppressWarnings("unchecked")
    public List<Meal> selectBoardList(Meal meal) throws Exception{
    	return (List<Meal>)selectList("meal.selectMealList", meal);
    }
 
}
