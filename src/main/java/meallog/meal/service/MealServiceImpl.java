package meallog.meal.service;


import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import meallog.common.dao.AbstractDAO;
import meallog.common.util.CommonUtils;
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

	@Override
	public List<Meal> selectBoardList(Meal meal) throws Exception {
		// TODO Auto-generated method stub
		return mealDAO.selectBoardList(meal);
	}

	@Override
	public List<Meal> selectUserMealList(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");
		log.debug("sessionMealList : "+session);
		return mealDAO.selectUserMealList(member);
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
		log.debug("[Meal Service] data : "+meal);
		Member member = (Member) session.getAttribute("member");
		String userName = member.getNick();
		meal.put("USERNICK", userName);
		mealDAO.insertBoard(meal);
		String filePath = session.getServletContext().getRealPath("");

		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(meal, filePath,request);
        for(int i=0, size=list.size(); i<size; i++){
        	if(i==0){
        		Map<String, Object> picMap = new HashMap<String, Object>();
        		picMap.put("IDX", meal.get("IDX").toString());
        		picMap.put("PICPATH", userName+"/"+list.get(0).get("STORED_FILE_NAME"));
        		mealDAO.updateFilePath(picMap);
        	}
        	mealDAO.insertFile(list.get(i));
        }
	}
	
	@Override
	public void insertMealMobile(Map<String, Object> meal, HttpServletRequest request,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	    
		log.debug("size : ");
		int i=0;
		
		while(iterator.hasNext()){
			log.debug("size : "+i);
			i++;
		}
		
		Map<String, Object> listMap = null; 
        
		MultipartFile multipartFile = null;
		String originalFileName = null;
	    String originalFileExtension = null;
	    String storedFileName = null;
	       

		log.debug("hasNext : ");
		while(iterator.hasNext()){
	           multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	           if(multipartFile.isEmpty() == false){
	               originalFileName = multipartFile.getOriginalFilename();
	               originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
	               
	               log.debug("0 : "+multipartFile.getContentType());
	               log.debug("0 : "+multipartFile.getName());
	               log.debug("0 : "+multipartFile.getSize());
	               log.debug("0 : "+multipartFile.getBytes());
	               log.debug("1 : "+originalFileName);
	               log.debug("2 : "+originalFileExtension);

	               
	           }
	       }
	    
		log.debug("insertBoard req : "+multipartHttpServletRequest.getContentLength());
		log.debug("insertBoard req : "+multipartHttpServletRequest.getContentType());
		log.debug("insertBoard req : "+multipartHttpServletRequest.getContextPath());
		log.debug("insertBoard req : "+multipartHttpServletRequest.getHeaderNames());

	}
}
