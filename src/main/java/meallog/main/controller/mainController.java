package meallog.main.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import meallog.common.common.CommandMap;
import meallog.common.dao.AbstractDAO;
import meallog.main.service.MainService;

@Controller
public class mainController {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
    
	@Resource(name="mainService")
    private MainService mainService;
     
    @RequestMapping(value="/main.do")
    public ModelAndView introMeallog(HttpSession session) throws Exception{
        ModelAndView mv;

        log.debug("main : "+session);
        log.debug("main : "+session.getSessionContext());
        log.debug("main : "+session.getValue("member"));
        if(session.getAttribute("member")!=null){
        	mv = new ModelAndView("redirect:/meal/main.do");
        }else{
        	mv = new ModelAndView("/main/meallogin");
        }
        return mv;
    }
}
