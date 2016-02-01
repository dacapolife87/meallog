package meallog.meal.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import meallog.common.dao.AbstractDAO;
import meallog.common.util.FileUtils;
import meallog.meal.dao.MealDAO;
import meallog.meal.vo.Meal;

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
	public void insertBoard(Map<String, Object> meal, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		mealDAO.insertBoard(meal);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(meal, request);
        for(int i=0, size=list.size(); i<size; i++){
        	mealDAO.insertFile(list.get(i));
        }


	}
 
}
