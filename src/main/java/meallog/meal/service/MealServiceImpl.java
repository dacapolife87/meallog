package meallog.meal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import meallog.common.dao.AbstractDAO;
import meallog.common.util.FileUtils;
import meallog.meal.dao.MealDAO;
import meallog.meal.vo.Meal;
import meallog.user.vo.Member;

@Service("mealService")
public class MealServiceImpl implements MealService{
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
    @Resource(name="mealDAO")
    private MealDAO mealDAO;

//	@Override
//	public List<Meal> selectBoardList(Meal meal) throws Exception {
//		// TODO Auto-generated method stub
//		return mealDAO.selectBoardList(meal);
//	}

	@Override
	public List<Meal> selectUserMealList(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");
		return mealDAO.selectUserMealList(member);
	}
	@Override
	public Meal selectUserOneMealList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return mealDAO.selectUserOneMealList(map);
	}
	@Override
 	public List<Meal> selectShareMealList(HttpSession session) throws Exception {
 		// TODO Auto-generated method stub
 		Member member = (Member) session.getAttribute("member");
 		return mealDAO.selectShareMealList(member);
 	}
 
	@Override
	public void insertMeal(Map<String, Object> meal, HttpServletRequest request,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");
		String userName = member.getNick();
		meal.put("USERNAME", userName);		
		insertMealFile(meal, request, session);
		
//		mealDAO.insertBoard(meal);
//		String filePath = session.getServletContext().getRealPath("");
//		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(meal, filePath, request);
//        for(int i=0, size=list.size(); i<size; i++){
//        	if(i==0){
//        		Map<String, Object> picMap = new HashMap<String, Object>();
//        		picMap.put("IDX", meal.get("IDX"));
//        		picMap.put("PICPATH", userName+"/"+list.get(0).get("STORED_FILE_NAME"));
//        		mealDAO.updateFilePath(picMap);
//        	}
//        	mealDAO.insertFile(list.get(i));
//        }
	}
	
	@Override
	public void insertMealMobile(Map<String, Object> meal, HttpServletRequest request,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");
		String userName = member.getNick();
		meal.put("USERNAME", userName);
		if(meal.get("SHARE").equals("true")){
			meal.put("SHARE", "1");
		}else{
			meal.put("SHARE", "0");
		}
		insertMealFile(meal, request, session);
		
//		mealDAO.insertBoard(meal);
//		String filePath = session.getServletContext().getRealPath("");
//		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(meal, filePath, request);
//        for(int i=0, size=list.size(); i<size; i++){
//        	if(i==0){
//        		Map<String, Object> picMap = new HashMap<String, Object>();
//        		picMap.put("IDX", meal.get("IDX"));
//        		picMap.put("PICPATH", meal.get("USERNAME")+"/"+list.get(0).get("STORED_FILE_NAME"));
//        		mealDAO.updateFilePath(picMap);
//        	}
//        	mealDAO.insertFile(list.get(i));
//        }		
	}
	@Override
	public void insertMealFile(Map<String, Object> meal, HttpServletRequest request, HttpSession session)
			throws Exception {
		// TODO Auto-generated method stub
		mealDAO.insertBoard(meal);
		String filePath = session.getServletContext().getRealPath("");
	
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(meal, filePath, request);
        for(int i=0, size=list.size(); i<size; i++){
        	if(i==0){
        		Map<String, Object> picMap = new HashMap<String, Object>();
        		picMap.put("IDX", meal.get("IDX"));
        		picMap.put("PICPATH", meal.get("USERNAME")+"/"+list.get(0).get("STORED_FILE_NAME"));
        		mealDAO.updateFilePath(picMap);
        	}
        	mealDAO.insertFile(list.get(i));
        }		
		
	}
	@Override
	public void updateMeal(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		mealDAO.updateMeal(map);
	}
	@Override
	public void deleteMeal(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		mealDAO.deleteMeal(map);
	}
	@Override
	public List<String> autocompleteMeal(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return mealDAO.autocompleteMeal(map);
	}
		
	//Popup Test2222
	@Override
	public Meal selectPopupMeal(Map<String, Object> map , HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member)session.getAttribute("member");
		Meal meal = mealDAO.selectPopupMeal(map);
		
		// 이 함수를 호출한 곳이 myMealPage 인지 아닌지 검사하는 조건문
		if(member.getNick().equals(meal.getUSERNAME())){
			meal.setCHECKUSER(true);
		}else{
			meal.setCHECKUSER(false);
		}
		
		return meal;
	}
	
	//Share 기능
	@Override
	public void updateShare(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		mealDAO.updateShareMeal(map);
	}

	
	
	
	
}
