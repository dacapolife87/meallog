
package meallog.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import meallog.common.dao.AbstractDAO;
import meallog.sample.vo.Meal;

@Repository("mealDAO")
public class MealDAO extends AbstractDAO{
 
    @SuppressWarnings("unchecked")
    public List<Meal> selectBoardList(Meal meal) throws Exception{
        return (List<Meal>)selectList("sample.selectBoardList", meal);
    }
 
}
