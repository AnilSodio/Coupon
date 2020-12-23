package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vc.thinker.apiv2.web.BaseController;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;

@RestController
@RequestMapping(value = "/wx", produces = { APPLICATION_JSON_VALUE })
public class WxController extends BaseController{
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@RequestMapping(value = "", method = RequestMethod.GET,produces = {"text/html; charset=UTF-8"})
	public ModelAndView wx(Model model){
		
		SysSetting info = sysSettingDao.findOne();
		model.addAttribute("info",info);
		
		return new ModelAndView("wx");
	}
}
