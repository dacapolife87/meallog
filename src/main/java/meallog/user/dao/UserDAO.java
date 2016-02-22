package meallog.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import meallog.common.dao.AbstractDAO;
import meallog.user.vo.Member;


@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	protected Log log = LogFactory.getLog(AbstractDAO.class);
	
	@SuppressWarnings("unchecked")
    public void joinMember(Map<String, Object> map) throws Exception{
        insert("member.joinMember", map);
    }
	
	@SuppressWarnings("unchecked")
    public Member loginMember(Map<String, Object> map) throws Exception{
		return selectExist("member.loginMember", map);

    }
	
	@SuppressWarnings("unchecked")
    public boolean idCheck(Map<String, Object> map) throws Exception{
		return selectCheckId("member.idCheck", map);

    }
}
