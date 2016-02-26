
package meallog.meal.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import meallog.common.dao.AbstractDAO;
import meallog.meal.vo.Meal;
import meallog.user.vo.Member;

@Repository("mealDAO")
public class MealDAO extends AbstractDAO{
	protected Log log = LogFactory.getLog(AbstractDAO.class);

//    @SuppressWarnings("unchecked")
//    public List<Meal> selectBoardList(Meal meal) throws Exception{
//    	return (List<Meal>)selectList("meal.selectMealList", meal);
//    }
    
    @SuppressWarnings("unchecked")
	public List<Meal> selectUserMealList(Member member) throws Exception{
    	return (List<Meal>)selectList("meal.selectUserMealList", member);
    }
    @SuppressWarnings("unchecked")
    public List<Meal> selectShareMealList(Member member) throws Exception{
    	return (List<Meal>)selectList("meal.selectShareMealList", member);
    }
    public Meal selectUserOneMealList(Map<String, Object> map) throws Exception{
    	return (Meal)selectList("meal.selectUserOneMealList", map);
    }
//    public void insertBoard(Map<String, Object> meal) throws Exception{
//    	insert("meal.insertBoard", meal);
//    }
    public void insertBoard(Meal meal) throws Exception{
    	insert("meal.insertBoardVO", meal);
    }
    public void insertFile(Map<String, Object> map) throws Exception{
        insert("meal.insertFile", map);
    }
    public void updateFilePath(Map<String, Object> map) throws Exception{
        insert("meal.updateFilePath", map);
    }
    public void updateMeal(Map<String, Object> map) throws Exception{
    	update("meal.updateUserMeal", map);
    }
    public void deleteMeal(Map<String, Object> map) throws Exception{
    	delete("meal.deleteUserMeal", map);
    }
    
    public List<String> autocompleteMeal(Map<String, Object> map) throws Exception{
		return (List<String>)selectList("meal.autocompleteMeal", map);
    }
    
    //Popup Test 222222222222
    public Meal selectPopupMeal(Map<String,Object> map) throws Exception{
    	return (Meal)selectOne("meal.selectPopupMeal", map);
    }
    
    //공유 버튼 업데이트
    public void updateShareMeal(Map<String,Object> map) throws Exception{
    	update("meal.shareMyMeal", map);
    }
    
}
