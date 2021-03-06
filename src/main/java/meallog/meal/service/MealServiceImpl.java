package meallog.meal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
		String json = (String) meal.get("meal");  
		JSONObject jsonObj = new JSONObject();  
		jsonObj = (JSONObject) JSONValue.parse(json);  
		
		 
		Map<String,Object> mealMap = new HashMap<String,Object>();  
		mealMap.put("CATEGORY",jsonObj.get("category"));  
		mealMap.put("CONTENT",jsonObj.get("content"));  
		mealMap.put("EATDATE",jsonObj.get("eatdate"));  
		mealMap.put("WHENEAT",jsonObj.get("wheneat"));  
		mealMap.put("NAME",jsonObj.get("name"));  
		mealMap.put("SHARE",jsonObj.get("share"));  
		mealMap.put("IDX",jsonObj.get("idx"));  
		
		
		Member member = (Member) session.getAttribute("member");
		String userName = member.getNick();
		mealMap.put("USERNAME", userName);
		if(mealMap.get("SHARE").equals("true")){
			mealMap.put("SHARE", "1");
		}else{
			mealMap.put("SHARE", "0");
		}
		
		insertMealFile(mealMap, request, session);
		
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
		
		
		// ���⑥닔瑜��몄텧��怨녹씠 myMealPage �몄� �꾨땶吏�寃�궗�섎뒗 議곌굔臾�
		if(member.getNick().equals(meal.getUSERNAME())){
			meal.setCHECKUSER(true);
		}else{
			meal.setCHECKUSER(false);

			map.put("USERNAME", member.getNick());
			
			Map<String,Object> checkMap = mealDAO.checkRecommendMeal(map);
			
			log.debug(checkMap);
			// 濡쒓렇�명븳 �좎�媛��꾩옱 �대┃���ъ쭊���대┃�덈뒗吏��섏� �딆븯�붿� 泥댄겕�섎뒗 �⑥닔
			if(checkMap == null){
				meal.setcheckrecommend(false);
			}else if(checkMap != null){
				meal.setcheckrecommend(true);
			}
			
		}
		
		return meal;
	}
	
	//Share 湲곕뒫
	@Override
	public void updateShare(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub

		mealDAO.updateShareMeal(map);
	}

	
	//異붿쿇 湲곕뒫
	@Override
	public void insertRecommend(Map<String, Object> map, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member)session.getAttribute("member");
		String username = member.getNick(); // 
		map.put("USERNAME", username);
		log.debug(map);
		mealDAO.insertRecommendMeal(map);
	}
	
	//異붿쿇 �댁젣 湲곕뒫
	@Override
	public void deleteRecommend(Map<String, Object> map, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
//		Member member = (Member)session.getAttribute("member");
//		String username = member.getNick();
//		map.put("USERNAME", username);
		log.debug(map);
		mealDAO.deleteRecommendMeal(map);
	}
	
	
	
	
}
