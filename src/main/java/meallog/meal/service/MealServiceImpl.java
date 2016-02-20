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

	@Override
	public List<Meal> selectBoardList(Meal meal) throws Exception {
		// TODO Auto-generated method stub
		return mealDAO.selectBoardList(meal);
	}

	@Override
	public List<Meal> selectUserMealList(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");
		
		return mealDAO.selectUserMealList(member);
	}
 
	@Override
	public void insertBoard(Map<String, Object> meal, HttpServletRequest request,HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		Member member = (Member) session.getAttribute("member");
		log.debug("session : "+member);
		String userName = member.getNick();
		meal.put("USERNICK", userName);
		mealDAO.insertBoard(meal);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(meal, request);
        for(int i=0, size=list.size(); i<size; i++){
        	if(i==0){
        		final String filePath = "C:\\meallog\\file\\";
        		Map<String, Object> picMap = new HashMap<String, Object>();
        		log.debug("meal IDX : "+meal.get("IDX").toString());
        		log.debug("meal file path : "+filePath+userName+"\\"+list.get(0).get("STORED_FILE_NAME"));
        		picMap.put("IDX", meal.get("IDX").toString());
        		picMap.put("PICPATH", filePath+userName+"\\"+list.get(0).get("STORED_FILE_NAME"));
        		mealDAO.updateFilePath(picMap);
        	}
        	mealDAO.insertFile(list.get(i));
        }
	}
}
