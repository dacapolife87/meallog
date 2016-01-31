package meallog.user.service;

import java.util.Map;

import meallog.user.vo.Member;

public interface UserService {

	void joinMember(Map<String, Object> map) throws Exception;
	Member loginMember(Map<String, Object> map) throws Exception;
	void logoutMember(Map<String, Object> map) throws Exception;
}

