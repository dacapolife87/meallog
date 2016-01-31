package meallog.main.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import meallog.main.dao.MainDAO;
import meallog.meal.dao.MealDAO;
import meallog.meal.vo.Meal;

@Service("mainService")
public class MainServiceImpl implements MainService{
	Logger log = Logger.getLogger(this.getClass());
	
//    @Resource(name="mainDAO")
//    private MainDAO mainDAO;
//
//	@Override
//	public void joinMember(Map<String, Object> map) throws Exception {
//		// TODO Auto-generated method stub
//		mainDAO.joinMember(map);
//		
//	}

}
