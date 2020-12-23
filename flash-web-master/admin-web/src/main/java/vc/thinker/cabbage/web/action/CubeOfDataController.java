package vc.thinker.cabbage.web.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.sinco.common.utils.DateUtils;
import vc.thinker.cabbage.stats.CubeOfDataService;
import vc.thinker.cabbage.stats.StatsConstants;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeStatsBO;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.utils.CommUtil;

/**
 * 
 * @ClassName: StatsController 
 * @Description: TODO(统计) 
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/rlStats")
public class CubeOfDataController extends BaseController {
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private CubeOfDataService realTimeStatsService;
	
	@Autowired
	private SysSettingDao sysSettingDao;

	@RequiresPermissions("sys:stats:realTime")
	@SecurityMapping(name="role.list",permGroup="role.statistical",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"","realTime"})
	public String realTime(String beginDate,String endDate,Model model) 
	{
		if (StringUtils.isBlank(beginDate)) 
		{
			beginDate = DateUtils.formatDate(DateUtils.addDays(new Date(), -6));
		}
		if (StringUtils.isBlank(endDate)) 
		{
			endDate = DateUtils.formatDate(new Date());
		}
		//今日
		RealTimeStatsBO total = realTimeStatsService.countByDate(null);
		//今日
		RealTimeStatsBO today = realTimeStatsService.countByDate(DateUtils.formatDate(new Date()));
		//昨日
		RealTimeStatsBO yes = realTimeStatsService.countByDate(DateUtils.formatDate(DateUtils.addDays(new Date(), -1)));
		//平均
//		RealTimeStatsBO avg = realTimeStatsService.countAvgByDate(beginDate,endDate);
		
//		model.addAttribute("setting", sysSettingDao.findOne());
		model.addAttribute("total", total);
		model.addAttribute("today", today);
		model.addAttribute("yes", yes);
//		model.addAttribute("avg", avg);
		
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		
		SysSetting setting = sysSettingDao.findOne();
		String defaultCurrency = setting.getPlayformDefaultCurrency();
		model.addAttribute("defaultCurrency",defaultCurrency);
		model.addAttribute("setting",setting);
		
		return "modules/stats/cubeOfData";
	}
	
	@RequiresPermissions("sys:stats:realTime")
	@SecurityMapping(name="role.list",permGroup="role.statistical",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping({"group"})
	public String group(String type,String day,String groupType,Model model) 
	{
		String date = DateUtils.formatDate(new Date());
		if(day.equals("yes")){
			date = DateUtils.formatDate(DateUtils.addDays(new Date(), -1));
		}
		String dayStr = date+"日";
		if(day.equals("total")){
			date = null;
			dayStr = "总";
		}
		
		StringBuffer groupName = new StringBuffer();
		StringBuffer value = new StringBuffer();
		List<RealTimeGroupStatsBO> list = Lists.newArrayList();
		
		if( groupType.equals("area")||groupType.equals("client_type")
				||groupType.equals("sex")||groupType.equals("vip_type")){
			if(type.equals("tripNum")||type.equals("distance")||type.equals("consume")){
				list = realTimeStatsService.findTripInfoByGroupType(groupType,date,type);
			}else if(type.equals("registNum")){
				list = realTimeStatsService.findRegistInfoByGroupType(groupType,date);
			}else if(type.equals("vipPay")){
				list = realTimeStatsService.findVipPayByGroupType(groupType,date);
			}else if(type.equals("balance")){
				list = realTimeStatsService.findBalanceByGoupType(groupType,date);
			}
		}
		if(groupType.equals("time")){
			//上午 下午 晚上 凌晨
			BigDecimal sw =  BigDecimal.ZERO;
			BigDecimal xw =  BigDecimal.ZERO;
			BigDecimal ws =  BigDecimal.ZERO;
			BigDecimal lc =  BigDecimal.ZERO;
			String querydate = date!=null?date+" ":"";
			if(type.equals("tripNum")||type.equals("distance")||type.equals("consume")){
				sw =  realTimeStatsService.findTripInfoByTimeRange(day,querydate+"06:00:00",querydate+"11:59:59",type);
				xw =  realTimeStatsService.findTripInfoByTimeRange(day,querydate+"12:00:00",querydate+"17:59:59",type);
				ws =  realTimeStatsService.findTripInfoByTimeRange(day,querydate+"18:00:00",querydate+"23:59:59",type);
				lc =  realTimeStatsService.findTripInfoByTimeRange(day,querydate+"00:00:00",querydate+"05:59:59",type);
			}else if(type.equals("registNum")){
				sw =  realTimeStatsService.findRegistByTimeRange(day,querydate+"06:00:00",querydate+"11:59:59");
				xw =  realTimeStatsService.findRegistByTimeRange(day,querydate+"12:00:00",querydate+"17:59:59");
				ws =  realTimeStatsService.findRegistByTimeRange(day,querydate+"18:00:00",querydate+"23:59:59");
				lc =  realTimeStatsService.findRegistByTimeRange(day,querydate+"00:00:00",querydate+"05:59:59");
			}else if(type.equals("vipPay")){
				sw =  realTimeStatsService.findVipPayByTimeRange(day,querydate+"06:00:00",querydate+"11:59:59");
				xw =  realTimeStatsService.findVipPayByTimeRange(day,querydate+"12:00:00",querydate+"17:59:59");
				ws =  realTimeStatsService.findVipPayByTimeRange(day,querydate+"18:00:00",querydate+"23:59:59");
				lc =  realTimeStatsService.findVipPayByTimeRange(day,querydate+"00:00:00",querydate+"05:59:59");
			}
			if(sw!=null&&sw.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("上午");
				gs.setValue(sw);
				list.add(gs);
			}
			if(xw!=null&&xw.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("下午");
				gs.setValue(xw);
				list.add(gs);
			}
			if(ws!=null&&ws.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("晚上");
				gs.setValue(ws);
				list.add(gs);
			}
			if(lc!=null&&lc.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("凌晨");
				gs.setValue(lc);
				list.add(gs);
			}
		}
		if(groupType.equals("age")){
			//年龄段
			BigDecimal sw =  BigDecimal.ZERO;
			BigDecimal xw =  BigDecimal.ZERO;
			BigDecimal ws =  BigDecimal.ZERO;
			BigDecimal lc =  BigDecimal.ZERO;
			BigDecimal other =  BigDecimal.ZERO;
			if(type.equals("tripNum")||type.equals("distance")||type.equals("consume")){
				BigDecimal total=realTimeStatsService.findTripInfoByAgeRange(null,null,date,type);
				sw =  realTimeStatsService.findTripInfoByAgeRange(12,20,date,type);
				xw =  realTimeStatsService.findTripInfoByAgeRange(21,30,date,type);
				ws =  realTimeStatsService.findTripInfoByAgeRange(31,40,date,type);
				lc =  realTimeStatsService.findTripInfoByAgeRange(41,null,date,type);
				other = total.subtract(sw).subtract(xw).subtract(ws).subtract(lc);
			}else if(type.equals("vipPay")){
				BigDecimal total=realTimeStatsService.findVipPayByAgeRange(null,null,date);
				sw =  realTimeStatsService.findVipPayByAgeRange(12,20,date);
				xw =  realTimeStatsService.findVipPayByAgeRange(21,30,date);
				ws =  realTimeStatsService.findVipPayByAgeRange(31,40,date);
				lc =  realTimeStatsService.findVipPayByAgeRange(41,null,date);
				other = total.subtract(sw).subtract(xw).subtract(ws).subtract(lc);
			}else if(type.equals("registNum")){
				BigDecimal total=realTimeStatsService.findRegistByAgeRange(null,null,date);
				sw =  realTimeStatsService.findRegistByAgeRange(12,20,date);
				xw =  realTimeStatsService.findRegistByAgeRange(21,30,date);
				ws =  realTimeStatsService.findRegistByAgeRange(31,40,date);
				lc =  realTimeStatsService.findRegistByAgeRange(41,null,date);
				other = total.subtract(sw).subtract(xw).subtract(ws).subtract(lc);
			}
			if(sw!=null&&sw.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("12岁-20岁");
				gs.setValue(sw);
				list.add(gs);
			}
			if(xw!=null&&xw.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("21岁-30岁");
				gs.setValue(xw);
				list.add(gs);
			}
			if(ws!=null&&ws.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("31岁-40岁");
				gs.setValue(ws);
				list.add(gs);
			}
			if(lc!=null&&lc.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("40岁以上");
				gs.setValue(lc);
				list.add(gs);
			}
			if(other!=null&&other.doubleValue()>0){
				RealTimeGroupStatsBO gs = new RealTimeGroupStatsBO();
				gs.setGroupName("未知");
				gs.setValue(other);
				list.add(gs);
			}
		}
			
		//组装数据
		for(RealTimeGroupStatsBO group:list){
			if(groupType.equals("client_type")){
				group.setGroupName(StatsConstants.getClientName(group.getGroupName()));
			}
			if(groupType.equals("sex")){
				group.setGroupName(StatsConstants.getSexName(CommUtil.null2Int(group.getGroupName())));
			}
			if(group.getValue()!=null){
				groupName.append(",'"+group.getGroupName()+"'");
				value.append(",{value:"+group.getValue()+",name:'"+group.getGroupName()+"'}");
			}
		}
		if(StringUtils.isNoneBlank(groupName)){
			model.addAttribute("groupName", "["+groupName.substring(1)+"]");
			model.addAttribute("value", "["+value.substring(1)+"]");
		}else{
			model.addAttribute("groupName", "[]");
			model.addAttribute("value", "[]");
		}
		//标题
		model.addAttribute("dayStr", dayStr);
		model.addAttribute("typeStr", StatsConstants.getTypeName(type)+"/"+StatsConstants.getTypeUnit(type));
		
		model.addAttribute("groupType", groupType);
		model.addAttribute("type", type);
		model.addAttribute("day", day);
		return "modules/stats/realTimeGroup";
	}
}
