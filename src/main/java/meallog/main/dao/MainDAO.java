package meallog.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import meallog.common.dao.AbstractDAO;


@Repository("mainDAO")
public class MainDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
    public void joinMember(Map<String, Object> map) throws Exception{
        insert("member.joinMember", map);
    }
}
