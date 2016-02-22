package meallog.user.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import meallog.user.vo.Member;

public interface UserService {

	void joinMember(Map<String, Object> map) throws Exception;
	boolean idCheck(Map<String, Object> map) throws Exception;
	Member loginMember(Map<String, Object> map) throws Exception;
	void logoutMember(Map<String, Object> map) throws Exception;

}

