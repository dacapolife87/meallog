package meallog.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import meallog.user.dao.UserDAO;
import meallog.user.vo.Member;

@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = Logger.getLogger(this.getClass());
	
    @Resource(name="userDAO")
    private UserDAO userDAO;

	@Override
	public void joinMember(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		userDAO.joinMember(map);
		
	}

	@Override
	public Member loginMember(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return userDAO.loginMember(map);
	}

	@Override
	public void logoutMember(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
	}

}
