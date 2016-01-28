package meallog.common.controller;


import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import meallog.common.dao.AbstractDAO;
import meallog.common.service.MainService;

@Controller
public class mainController {
	protected Log log = LogFactory.getLog(AbstractDAO.class);
    
	@Resource(name="mainService")
    private MainService mainService;
     
    @RequestMapping(value="/main.do")
    public ModelAndView introMeallog() throws Exception{
    	log.debug("test1");
        ModelAndView mv = new ModelAndView("/main/meallogin");
        log.debug("test2");
        return mv;
    }
}
