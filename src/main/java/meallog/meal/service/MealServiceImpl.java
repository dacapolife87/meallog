package meallog.meal.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import meallog.common.common.ConvertData;
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
	
	@Resource(name="convertData")
	private ConvertData convertData;

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
		Meal mealVO = new Meal();
		log.debug("[Meal Service] data : "+meal);
		convertData.convertMapToObject(meal, mealVO);
		log.debug("vo : "+mealVO.getCATEGORY());
		Member member = (Member) session.getAttribute("member");
		String userName = member.getNick();
		//meal.put("USERNICK", userName);
		mealVO.setUSERNAME(userName);
		mealDAO.insertBoard(mealVO);
		log.debug("insert : ");
		String filePath = session.getServletContext().getRealPath("");

		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(mealVO, filePath, request);
        for(int i=0, size=list.size(); i<size; i++){
        	if(i==0){
        		Map<String, Object> picMap = new HashMap<String, Object>();
        		picMap.put("IDX", Integer.toString(mealVO.getIDX()));
        		picMap.put("PICPATH", userName+"/"+list.get(0).get("STORED_FILE_NAME"));
        		mealDAO.updateFilePath(picMap);
        	}
        	mealDAO.insertFile(list.get(i));
        }
	}
	
	@Override
	public void insertMealMobile(Map<String, Object> meal, HttpServletRequest request,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		//log.debug("[Meal Service] insertMealMobile : "+meal);
		
//		Gson gson = new Gson();
//    	gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
//    	Meal mealVO = gson.fromJson((String) meal.get("meal"), Meal.class);
//    	Member member = (Member) session.getAttribute("member");
//		String userName = member.getNick();
//		mealVO.setUSERNAME(userName);
//		mealDAO.insertBoard(mealVO);
//		String filePath = session.getServletContext().getRealPath("");
//		String jsonFile = (String) meal.get("image");
		Meal mealVO = new Meal();
		log.debug("[Meal Service] data : "+meal);
		convertData.convertMapToObject(meal, mealVO);
		log.debug("vo : "+mealVO.getCATEGORY());
		
		log.debug("meal vo change");
		Member member = (Member) session.getAttribute("member");
		String userName = member.getNick();
		//meal.put("USERNICK", userName);
		mealVO.setUSERNAME(userName);
		log.debug("meal 1");
		mealDAO.insertBoard(mealVO);
		log.debug("insert ");
		String filePath = session.getServletContext().getRealPath("");
		
		log.debug("file pre");
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfoMobile(mealVO, filePath, request);
        for(int i=0, size=list.size(); i<size; i++){
        	if(i==0){
        		Map<String, Object> picMap = new HashMap<String, Object>();
        		picMap.put("IDX", Integer.toString(mealVO.getIDX()));
        		picMap.put("PICPATH", userName+"/"+list.get(0).get("STORED_FILE_NAME"));
        		mealDAO.updateFilePath(picMap);
        	}
        	mealDAO.insertFile(list.get(i));
        }
        
//		String json = (String) meal.get("meal");
//		JSONObject jsonObj = new JSONObject();
//		jsonObj = (JSONObject) JSONValue.parse(json);
//		log.debug("str json : "+json);
//		log.debug("str json : "+jsonObj);
//		log.debug("get json : "+jsonObj.get("category"));
		
//		Meal mealVO = new Meal();
//		mealVO.setcategory((String) jsonObj.get("category"));
//		mealVO.setcontent((String) jsonObj.get("content"));
//		mealVO.seteatdate((Date) jsonObj.get("eatdate"));
//		mealVO.setwheneat((String) jsonObj.get("wheneat"));
//		mealVO.setname((String) jsonObj.get("name"));
//		mealVO.setshare( (Boolean) jsonObj.get("share"));

//		Map<String,Object> mealMap = new HashMap<String,Object>();
//		mealMap.put("CATEGORY",jsonObj.get("category"));
//		mealMap.put("CONTENT",jsonObj.get("content"));
//		mealMap.put("EATDATE",jsonObj.get("eatdate"));
//		mealMap.put("WHENEAT",jsonObj.get("wheneat"));
//		mealMap.put("NAME",jsonObj.get("name"));
//		mealMap.put("SHARE",jsonObj.get("share"));
//		mealMap.put("IDX",jsonObj.get("idx"));
    	
		
		//mealVO.setusername(userName);
		//mealMap.put("USERNICK", userName);
		
		
//		List<Map<String,Object>> list = fileUtils.parseInsertFileInfoMobile(mealMap, filePath,meal);
//        for(int i=0, size=list.size(); i<size; i++){
//        	if(i==0){
//        		Map<String, Object> picMap = new HashMap<String, Object>();
//        		picMap.put("IDX", meal.get("IDX").toString());
//        		picMap.put("PICPATH", userName+"/"+list.get(0).get("STORED_FILE_NAME"));
//        		mealDAO.updateFilePath(picMap);
//        	}
//        	mealDAO.insertFile(list.get(i));
//        }
        
//		Member member = (Member) session.getAttribute("member");
//		String img = (String) meal.get("image");
//		log.debug("img str : "+img);
//		String text = new String((byte[]) meal.get("image"), "UTF-8");
//		log.debug("test : "+text);
//		
//		MediaType mt = MediaType.parseMediaType(text);
//		log.debug("mt : "+mt);
//		log.debug("insert mobile 2");
//		String userName = member.getNick();
//		meal.put("USERNICK", userName);
//		mealDAO.insertBoard(meal);
//		String filePath = session.getServletContext().getRealPath("");
//		log.debug("insert mobile 3"); 
//		List<Map<String,Object>> list = fileUtils.parseInsertFileInfoMobile(meal, filePath, multipartFile);
//        for(int i=0, size=list.size(); i<size; i++){
//        	if(i==0){
//        		Map<String, Object> picMap = new HashMap<String, Object>();
//        		picMap.put("IDX", meal.get("IDX").toString());
//        		picMap.put("PICPATH", userName+"/"+list.get(0).get("STORED_FILE_NAME"));
//        		mealDAO.updateFilePath(picMap);
//        	}
//        	mealDAO.insertFile(list.get(i));
//        }
		
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
		if(member.getNick().equals(meal.getusername())){
			meal.setcheckuser(true);
		}else{
			meal.setcheckuser(false);
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
