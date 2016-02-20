
package meallog.meal.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import meallog.common.dao.AbstractDAO;
import meallog.meal.vo.Meal;
import meallog.user.vo.Member;

@Repository("mealDAO")
public class MealDAO extends AbstractDAO{
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
	
    @SuppressWarnings("unchecked")
    public List<Meal> selectBoardList(Meal meal) throws Exception{
    	return (List<Meal>)selectList("meal.selectMealList", meal);
    }
    
    @SuppressWarnings("unchecked")
	public List<Meal> selectUserMealList(Member member) throws Exception{
    	return (List<Meal>)selectList("meal.selectUserMealList", member);
    }
    
    @SuppressWarnings("unchecked")
    public List<Meal> selectShareMealList(Member member) throws Exception{
    	return (List<Meal>)selectList("meal.selectShareMealList", member);
    }

    public void insertBoard(Map<String, Object> meal) throws Exception{
        log.debug("test1 : "+meal);
    	insert("meal.insertBoard", meal);
    	log.debug("test2 : "+meal);
    }
    
    public void insertFile(Map<String, Object> map) throws Exception{
        insert("meal.insertFile", map);
    }
    
    public void updateFilePath(Map<String, Object> map) throws Exception{
        insert("meal.updateFilePath", map);
    }

 
}
