package vc.thinker.cabbage.web.action;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import vc.thinker.cabbage.stats.ReportStatsService;
import vc.thinker.cabbage.stats.bo.ReportDataBO;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;

/**
 * 
 * @ClassName: ReportStatsController 
 * @Description: (报表统计) 
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/reportStats")
public class ReportStatsController extends BaseController {
	
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private ReportStatsService reportStatsService;

	@RequiresPermissions("sys:stats:realTime")
	@SecurityMapping(name="list",permGroup="Statistical management",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"","reportForm"})
	public String reportForm(String year,String month,Model model) 
	{
		if (StringUtils.isBlank(year)&&StringUtils.isBlank(month)) 
		{
			Calendar date = Calendar.getInstance();
			year = date.get(Calendar.YEAR)+"";
		}
		if(StringUtils.isBlank(month)){
			ReportDataBO report = reportStatsService.getReportStats(year);
			model.addAttribute("json", JSONObject.toJSONString(report));
		}else{
			ReportDataBO report = reportStatsService.getReportStats(year,month);
			model.addAttribute("json", JSONObject.toJSONString(report));
		}
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "modules/stats/reportStats";
	}
	
	
}
