package vc.thinker.cabbage.web.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.sinco.common.utils.DateUtils;

import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.SeCommonConstants;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.stats.SimpleStatsService;
import vc.thinker.cabbage.stats.StatsService;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.StatsItem;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.contants.SysUserContant;

/**
 * @ClassName: StatsController
 * @Description:
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/stats")
public class StatsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(StatsController.class);

	@Value("${adminPath}")
	private String adminPath;

	@Autowired
	private StatsService statsService;

	@Autowired
	private SimpleStatsService simpleStatsService;

	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private CountryService countryService;
	

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "userStats" })
	public String userStats(Model model, @ModelAttribute("vo") StatsVO vo) {
		Integer timeNums;
		Integer arraySize;
		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		List<CountStatsBO> list = statsService.findUserStats(vo);
		String timeStr[] = new String[timeNums];
		String numStr[] = new String[arraySize];

		for (int i = 0; i < arraySize; i++) {
			numStr[i] = "0";
		}

		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}

		if (list != null && list.size() > 0) {
			list.forEach(e -> {
				for (int i = 1; i <= arraySize; i++) {
					if (Integer.parseInt(e.getStatsTime()) == i) {
						numStr[i - 1] = e.getUserNums().toString();
					}
				}
			});
		}
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("numStr", Arrays.toString(numStr));
		return "modules/stats/userStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "depositStats" })
	public String depositStats(Model model, @ModelAttribute("vo") StatsVO vo) {
		Integer timeNums;
		Integer arraySize;
		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		// 押金增量
		List<CountStatsBO> list = statsService.findDepositStats(vo);
		// 当前押金
		List<CountStatsBO> current_list = statsService.statisticsCurrentDeposit(vo);

		String timeStr[] = new String[timeNums];
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + unit + (i + 1) + "'";
		}
		// 货币种类
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
		}
		
		if (list != null && list.size() > 0) {
			list.forEach(e -> {
				DataStats d = stats.get(e.getCurrency());
				if (null != d) {
					List<BigDecimal> growArr = d.getGrowArr();
					for (int i = 1; i <= arraySize; i++) {
						growArr.add(BigDecimal.ZERO);//图形控件不能为null
						if (Integer.parseInt(e.getStatsTime()) == i) {
							if (stats.containsKey(e.getCurrency())) {
								growArr.add(i-1, e.getDeposit());
							}
						} 
					}
				}
			});
		}

		
		if (null != current_list && current_list.size() > 0) {
			current_list.forEach(e -> {
				DataStats d = stats.get(e.getCurrency());
				if (null != d) {
					List<BigDecimal> numArr = d.getNumArr();
					for (int i = 1; i <= arraySize; i++) {
						numArr.add(BigDecimal.ZERO);
						if (Integer.parseInt(e.getStatsTime()) == i) {
							if (stats.containsKey(e.getCurrency())) {
								numArr.add(i-1, e.getDeposit());
							}
						}
					}
				}
			});
			// return 
			StringBuffer growsb = new StringBuffer();
			StringBuffer numsb = new StringBuffer();
			for(Map.Entry<String, DataStats> entry : stats.entrySet()){
			    String mapKey = entry.getKey();
			    DataStats mapValue = entry.getValue();
			    if (!mapValue.getGrowArr().isEmpty()) {
			    	growsb.append("{name:'"+mapKey+"',type:'line',data:"+Arrays.toString(mapValue.getGrowArr().toArray())+"},");
				}
			    if (!mapValue.getNumArr().isEmpty()) {
			    	numsb.append("{name:'"+mapKey+"',type:'line',data:"+Arrays.toString(mapValue.getNumArr().toArray())+"},");
				}
			}
			model.addAttribute("growseries", StringUtils.removeEnd(growsb.toString(), ","));
			model.addAttribute("numseries", StringUtils.removeEnd(numsb.toString(), ","));
		}
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("vo", vo);

		return "modules/stats/depositStats";
	}
	

	private int getDaysOfMonth(String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(month + "-1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "cabinetStats" })
	public String cabinetStats(Model model, @ModelAttribute("vo") StatsVO vo) {

		Integer timeNums;
		Integer arraySize;

		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "month";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "number";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		String timeStr[] = new String[timeNums];
		String numStr[] = new String[arraySize];
		String numStr2[] = new String[arraySize];
		for (int i = 0; i < arraySize; i++) {
			numStr[i] = "0";
			numStr2[i] = "0";
		}
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}

		List<CountStatsBO> total_list = statsService.statsTotalCabinet(vo);
		List<CountStatsBO> normal_list = statsService.statsNormalCabinet(vo);

		if (null != total_list && total_list.size() > 0) {
			total_list.forEach(e -> {
				for (int i = 1; i <= arraySize; i++) {
					if (Integer.parseInt(e.getStatsTime()) == i) {
						Integer sum = null != e.getSum() ? e.getSum() : 0;
						numStr[i - 1] = sum.toString();
					}
				}
			});
		}

		if (null != normal_list && normal_list.size() > 0) {
			normal_list.forEach(e -> {
				for (int i = 1; i <= arraySize; i++) {
					if (Integer.parseInt(e.getStatsTime()) == i) {
						Integer sum = null != e.getSum() ? e.getSum() : 0;
						numStr2[i - 1] = sum.toString();
					}
				}
			});
		}

		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("numStr", Arrays.toString(numStr));
		model.addAttribute("numStr2", Arrays.toString(numStr2));

		return "modules/stats/cabinetStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "pbStats" })
	public String pbStats(Model model, StatsVO vo) {

		Integer timeNums;
		Integer arraySize;

		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "month";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "number";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		String timeStr[] = new String[timeNums];
		String numStr[] = new String[arraySize];
		for (int i = 0; i < arraySize; i++) {
			numStr[i] = "0";
		}
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}

		List<CountStatsBO> total_list = statsService.statsNormalCabinet(vo);

		if (null != total_list && total_list.size() > 0) {
			total_list.forEach(e -> {
				for (int i = 1; i <= arraySize; i++) {
					if (Integer.parseInt(e.getStatsTime()) == i) {
						Integer sum = null != e.getSum() ? e.getSum() : 0;
						numStr[i - 1] = sum.toString();
					}
				}
			});
		}

		model.addAttribute("vo", vo);
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("numStr", Arrays.toString(numStr));

		return "modules/stats/pbStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "percentStats" })
	public String percentStats(Model model) {

		List<StatsItem> stats = simpleStatsService.getTripStats();
		model.addAttribute("stats", stats);

		return "modules/stats/percentStats";

	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "consumeStats" })
	public String consumeStats(Model model, @ModelAttribute("vo") StatsVO vo) {

		Integer timeNums;
		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}
		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "";
		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "";
		}
		List<CountStatsBO> list = statsService.orderConsumeStats(vo);
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
		}
		
		String timeStr[] = new String[timeNums];
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}

		if (list != null && list.size() > 0) {
			list.forEach(e -> {
				DataStats d = stats.get(e.getCurrency());
				if (null != d) {
					List<BigDecimal> growArr = d.getGrowArr();
					for (int i = 1; i <= timeNums; i++) {
						growArr.add(BigDecimal.ZERO);
						if (Integer.parseInt(e.getStatsTime()) == i && !StringUtils.isEmpty(e.getCurrency())) {
							if (stats.containsKey(e.getCurrency())) {
								growArr.add(i - 1, e.getConsume());
							}
						} 
					}
				}
			});
			// return json 
			StringBuffer growsb = new StringBuffer();
			for(Map.Entry<String, DataStats> entry : stats.entrySet()){
			    String mapKey = entry.getKey();
			    DataStats mapValue = entry.getValue();
			    if (!mapValue.getGrowArr().isEmpty()) {
			    	growsb.append("{name:'"+mapKey+"',type:'line',data:"+Arrays.toString(mapValue.getGrowArr().toArray())+"},");
				}
			}
			model.addAttribute("growseries", StringUtils.removeEnd(growsb.toString(), ","));
		}
		
		model.addAttribute("vo", vo);
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		return "modules/stats/consumeStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "memberConsumeStats" })
	public String memberConsumeStats(Model model, @ModelAttribute("vo") StatsVO vo) {

		Integer timeNums;
		Integer arraySize;
		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		vo.setPayType(OrderConstants.PAY_TYPE_VIP);
		List<CountStatsBO> list = statsService.orderConsumeStats(vo);
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
		}
		String timeStr[] = new String[timeNums];
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}
		StringBuffer growsb = new StringBuffer();
		if (list != null && list.size() > 0) {
			list.forEach(e -> {
				DataStats d = stats.get(e.getCurrency());
				if (null != d) {
					List<BigDecimal> growArr = d.getGrowArr();
					for (int i = 1; i <= arraySize; i++) {
						growArr.add(BigDecimal.ZERO);
						if (Integer.parseInt(e.getStatsTime()) == i && !StringUtils.isEmpty(e.getCurrency())) {
							if (stats.containsKey(e.getCurrency())) {
								growArr.add(i - 1, e.getConsume());
							}
						} 
					}
				}
			});
			// return 
			for(Map.Entry<String, DataStats> entry : stats.entrySet()){
			    String mapKey = entry.getKey();
			    DataStats mapValue = entry.getValue();
			    if (!mapValue.getGrowArr().isEmpty()) {
			    	growsb.append("{name:'"+mapKey+"',type:'line',data:"+Arrays.toString(mapValue.getGrowArr().toArray())+"},");
				}
			}
			model.addAttribute("growseries", StringUtils.removeEnd(growsb.toString(), ","));
		}

		model.addAttribute("vo", vo);
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		return "modules/stats/memberConsumeStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "balanceConsumeStats" })
	public String balanceConsumeStats(Model model, @ModelAttribute("vo") StatsVO vo) {

		Integer timeNums;
		Integer arraySize;
		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		vo.setPayType(OrderConstants.PAY_TYPE_BALANCE);
		List<CountStatsBO> list = statsService.orderConsumeStats(vo);
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
		}
		String timeStr[] = new String[timeNums];
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}
		
		
		if (list != null && list.size() > 0) {
			list.forEach(e -> {
				DataStats d = stats.get(e.getCurrency());
				if (null != d) {
					List<BigDecimal> growArr = d.getGrowArr();
					for (int i = 1; i <= arraySize; i++) {
						growArr.add(BigDecimal.ZERO);
						if (Integer.parseInt(e.getStatsTime()) == i && !StringUtils.isEmpty(e.getCurrency())) {
							if (stats.containsKey(e.getCurrency())) {
								growArr.add(i - 1, e.getConsume());
							}
						} 
					}
				}
			});
			// return json 
			StringBuffer growsb = new StringBuffer();
			for(Map.Entry<String, DataStats> entry : stats.entrySet()){
			    String mapKey = entry.getKey();
			    DataStats mapValue = entry.getValue();
			    if (!mapValue.getGrowArr().isEmpty()) {
			    	growsb.append("{name:'"+mapKey+"',type:'line',data:"+Arrays.toString(mapValue.getGrowArr().toArray())+"},");
				}
			}
			model.addAttribute("growseries", StringUtils.removeEnd(growsb.toString(), ","));
		}


		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("vo", vo);
		return "modules/stats/balanceConsumeStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "feedbackStats" })
	public String feedbackStats(Model model, StatsVO vo) {
		Integer timeNums;
		Integer arraySize;
		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}
		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "month";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "number";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		final int offset = vo.getStatsType().equals("1") ? 0 : -1;

		List<CountStatsBO> list = statsService.feedbackStats(vo);

		String timeStr[] = new String[timeNums];
		String numStr[] = new String[arraySize];
		String numStr1[] = new String[arraySize];// 1：首页，，
		String numStr2[] = new String[arraySize]; // 2：行程中
		String numStr3[] = new String[arraySize];// 3：已完成
		for (int i = 0; i < arraySize; i++) {
			numStr[i] = "0";
			numStr1[i] = "0";
			numStr2[i] = "0";
			numStr3[i] = "0";
		}
		for (int i = 0; i < timeNums; i++) {
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}

		if (list != null && list.size() > 0) {
			list.stream().collect(Collectors.groupingBy(p -> p.getStatsTime())).forEach((time, countStatsVOS) -> {
				int index = Integer.parseInt(time) + offset;
				numStr[index] = countStatsVOS.size() + "";
				countStatsVOS.stream().collect(Collectors.groupingBy(p -> p.getFeedType()))
						.forEach((type, sameTypes) -> {
							if (SeCommonConstants.FEEDBACK_FEED_TYPE_1.equals(type)) {
								numStr1[index] = sameTypes.size() + "";
							} else if (SeCommonConstants.FEEDBACK_FEED_TYPE_2.equals(type)) {
								numStr2[index] = sameTypes.size() + "";
							} else if (SeCommonConstants.FEEDBACK_FEED_TYPE_3.equals(type)) {
								numStr3[index] = sameTypes.size() + "";
							}
						});
			});
		}

		model.addAttribute("vo", vo);
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("numStr", Arrays.toString(numStr));
		model.addAttribute("numStr1", Arrays.toString(numStr1));
		model.addAttribute("numStr2", Arrays.toString(numStr2));
		model.addAttribute("numStr3", Arrays.toString(numStr3));

		return "modules/stats/feedbackStats";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "agentIncomeStats" })
	public String agentIncomeStats(Model model, StatsVO vo) {
		if (null == vo.getLimitNum()) {
			vo.setLimitNum(10);
		}
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
			vo.setCurrency(currency);
			List<UserRebateLogBO> list = statsService.agentIncomeStats(vo);
			if (null != list && list.size() > 0) {
				List<String> nameArr = ds.getNameArr();
				List<BigDecimal> numArr = ds.getNumArr();
				for (int i = 0; i < list.size(); i++) {
					nameArr.add(list.size() - 1 - i, "'" + list.get(i).getAgentName() + "'");
					numArr.add(list.size() - 1 - i,BigDecimal.ZERO);
					numArr.add(list.size() - 1 - i, list.get(i).getRebateAmount());
				}
			}
		}

		model.addAttribute("stats", stats);
		model.addAttribute("vo", vo);

		return "modules/stats/agentIncomeOrder";

	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "refereeIncomeStats" })
	public String refereeIncomeStats(Model model, StatsVO vo) {

		if (null == vo.getLimitNum()) {
			vo.setLimitNum(10);
		}
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
			vo.setCurrency(currency);
			List<UserRebateLogBO> list = statsService.refereeIncomeStats(vo);
			if (null != list && list.size() > 0) {
				List<String> nameArr = ds.getNameArr();
				List<BigDecimal> numArr = ds.getNumArr();
				for (int i = 0; i < list.size(); i++) {
					nameArr.add(list.size() - 1 - i, "'" + list.get(i).getAgentName() + "'");
					numArr.add(list.size() - 1 - i,BigDecimal.ZERO);
					numArr.add(list.size() - 1 - i, list.get(i).getRebateAmount());
				}
			}
		}

//		String firstNum1[] = new String[vo.getLimitNum()];
//		String firstNum2[] = new String[vo.getLimitNum()];
//		String firstNum3[] = new String[vo.getLimitNum()];
//
//		String sum1[] = new String[vo.getLimitNum()];
//		String sum2[] = new String[vo.getLimitNum()];
//		String sum3[] = new String[vo.getLimitNum()];
//
//		for (int i = 0; i < vo.getLimitNum(); i++) {
//
//			firstNum1[i] = "''";
//			firstNum2[i] = "''";
//			firstNum3[i] = "''";
//			sum1[i] = "0";
//			sum2[i] = "0";
//			sum3[i] = "0";
//		}
//
//		vo.setCurrency(CommonConstants.CURRENCY_CNY);
//		List<UserRebateLogBO> cny_list = statsService.refereeIncomeStats(vo);
//
//		if (null != cny_list && cny_list.size() > 0) {
//			for (int i = 0; i < cny_list.size(); i++) {
//				firstNum1[cny_list.size() - 1 - i] = "'" + cny_list.get(i).getRefereeName() + "'";
//				sum1[cny_list.size() - 1 - i] = cny_list.get(i).getRebateAmount().toString();
//			}
//		}
//
//		vo.setCurrency(CommonConstants.CURRENCY_MYR);
//		List<UserRebateLogBO> myr_list = statsService.refereeIncomeStats(vo);
//
//		if (null != myr_list && myr_list.size() > 0) {
//			for (int i = 0; i < myr_list.size(); i++) {
//				firstNum2[myr_list.size() - 1 - i] = "'" + myr_list.get(i).getRefereeName() + "'";
//				sum2[myr_list.size() - 1 - i] = myr_list.get(i).getRebateAmount().toString();
//			}
//		}
//
//		vo.setCurrency(CommonConstants.CURRENCY_SGD);
//		List<UserRebateLogBO> sgd_list = statsService.refereeIncomeStats(vo);
//		if (null != sgd_list && sgd_list.size() > 0) {
//			for (int i = 0; i < sgd_list.size(); i++) {
//				firstNum3[sgd_list.size() - 1 - i] = "'" + sgd_list.get(i).getRefereeName() + "'";
//				sum3[sgd_list.size() - 1 - i] = sgd_list.get(i).getRebateAmount().toString();
//			}
//		}
		model.addAttribute("stats", stats);
		model.addAttribute("vo", vo);
//		model.addAttribute("firstNum1", Arrays.toString(firstNum1));
//		model.addAttribute("sum1", Arrays.toString(sum1));
//		model.addAttribute("firstNum2", Arrays.toString(firstNum2));
//		model.addAttribute("sum2", Arrays.toString(sum2));
//		model.addAttribute("firstNum3", Arrays.toString(firstNum3));
//		model.addAttribute("sum3", Arrays.toString(sum3));

		return "modules/stats/refereeIncomeOrder";

	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "sellerIncomeStats" })
	public String sellerIncomeStats(Model model, StatsVO vo) {

		if (null == vo.getLimitNum()) {
			vo.setLimitNum(10);
		}
		
		Map<String, DataStats> stats = new HashMap<String, DataStats>();
		Set<String> currencies = countryService.findCurrencies();
		for (String currency : currencies) {
			DataStats ds = new DataStats();
			ds.setCurrency(currency);
			stats.put(currency, ds);
			vo.setCurrency(currency);
			List<UserRebateLogBO> list = statsService.sellerIncomeStats(vo);
			if (null != list && list.size() > 0) {
				List<String> nameArr = ds.getNameArr();
				List<BigDecimal> numArr = ds.getNumArr();
				for (int i = 0; i < list.size(); i++) {
					nameArr.add(list.size() - 1 - i, "'" + list.get(i).getAgentName() + "'");
					numArr.add(list.size() - 1 - i,BigDecimal.ZERO);
					numArr.add(list.size() - 1 - i, list.get(i).getRebateAmount());
				}
			}
		}

//		String firstNum1[] = new String[vo.getLimitNum()];
//		String firstNum2[] = new String[vo.getLimitNum()];
//		String firstNum3[] = new String[vo.getLimitNum()];
//
//		String sum1[] = new String[vo.getLimitNum()];
//		String sum2[] = new String[vo.getLimitNum()];
//		String sum3[] = new String[vo.getLimitNum()];
//
//		for (int i = 0; i < vo.getLimitNum(); i++) {
//
//			firstNum1[i] = "''";
//			firstNum2[i] = "''";
//			firstNum3[i] = "''";
//			sum1[i] = "0";
//			sum2[i] = "0";
//			sum3[i] = "0";
//		}
//
//		vo.setCurrency(CommonConstants.CURRENCY_CNY);
//		List<UserRebateLogBO> cny_list = statsService.sellerIncomeStats(vo);
//
//		if (null != cny_list && cny_list.size() > 0) {
//			for (int i = 0; i < cny_list.size(); i++) {
//				firstNum1[cny_list.size() - 1 - i] = "'" + cny_list.get(i).getSellerName() + "'";
//				sum1[cny_list.size() - 1 - i] = cny_list.get(i).getRebateAmount().toString();
//			}
//		}
//
//		vo.setCurrency(CommonConstants.CURRENCY_MYR);
//		List<UserRebateLogBO> myr_list = statsService.sellerIncomeStats(vo);
//
//		if (null != myr_list && myr_list.size() > 0) {
//			for (int i = 0; i < myr_list.size(); i++) {
//				firstNum2[myr_list.size() - 1 - i] = "'" + myr_list.get(i).getSellerName() + "'";
//				sum2[myr_list.size() - 1 - i] = myr_list.get(i).getRebateAmount().toString();
//			}
//		}
//
//		vo.setCurrency(CommonConstants.CURRENCY_SGD);
//		List<UserRebateLogBO> sgd_list = statsService.sellerIncomeStats(vo);
//		if (null != sgd_list && sgd_list.size() > 0) {
//			for (int i = 0; i < sgd_list.size(); i++) {
//				firstNum3[sgd_list.size() - 1 - i] = "'" + sgd_list.get(i).getSellerName() + "'";
//				sum3[sgd_list.size() - 1 - i] = sgd_list.get(i).getRebateAmount().toString();
//			}
//		}
		model.addAttribute("stats", stats);
		model.addAttribute("vo", vo);
//		model.addAttribute("firstNum1", Arrays.toString(firstNum1));
//		model.addAttribute("sum1", Arrays.toString(sum1));
//		model.addAttribute("firstNum2", Arrays.toString(firstNum2));
//		model.addAttribute("sum2", Arrays.toString(sum2));
//		model.addAttribute("firstNum3", Arrays.toString(firstNum3));
//		model.addAttribute("sum3", Arrays.toString(sum3));

		return "modules/stats/sellerIncomeOrder";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "reset" })
	public String reset(Model model, @ModelAttribute("beginDate") String beginDate,
			@ModelAttribute("endDate") String endDate) {
		if (StringUtils.isBlank(beginDate) && StringUtils.isBlank(endDate)) {
			endDate = DateUtils.formatDate(new Date());
			beginDate = DateUtils.formatDate(DateUtils.addDays(new Date(), -7));
		}
		if (StringUtils.isBlank(beginDate) && StringUtils.isNotBlank(endDate)) {
			beginDate = DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(endDate), -7));
		}
		if (StringUtils.isNotBlank(beginDate) && StringUtils.isBlank(endDate)) {
			endDate = DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(beginDate), 7));
		}
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		return "modules/stats/stats_reset";
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "role.list", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "resetSave" })
	public String resetSave(Model model, RedirectAttributes redirectAttributes, String beginDate, String endDate) {
		statsService.resetData(beginDate, endDate);
		addMessage(redirectAttributes, "Congratulations, proofreading success");
		return "redirect:" + adminPath + "/sys/stats/reset?beginDate=" + beginDate + "&endDate=" + endDate;
	}

	@RequiresPermissions("sys:stats:Statistics")
	@SecurityMapping(name = "Statistics", permGroup = "role.statistical", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "platformIncomeStats" })
	public String platformIncomeStats(Model model, @ModelAttribute("vo") StatsVO vo) {

		Integer timeNums;
		Integer arraySize;

		String unit = "";
		if (StringUtils.isEmpty(vo.getStatsType())) {
			vo.setStatsType("2");
		}
		if (vo.getStatsType().equals("1")) {
			vo.setTime(vo.getYear());
		} else {
			vo.setTime(vo.getMonth());
		}

		Calendar cal = Calendar.getInstance();

		if (vo.getTime() == null || vo.getTime().equals("")) {
			if (vo.getStatsType().equals("1")) {
				Calendar a = Calendar.getInstance();
				vo.setTime(a.get(Calendar.YEAR) + "");
				vo.setYear(vo.getTime());
			}
			if (vo.getStatsType().equals("2")) {
				Date date = new Date();
				vo.setTime(DateUtils.formatDate(date, "yyyy-MM"));
				vo.setMonth(vo.getTime());
			}
		}

		if (vo.getStatsType().equals("1")) {
			timeNums = 12;
			unit = "month";
			if (cal.get(Calendar.YEAR) == Integer.parseInt(vo.getTime().split("-")[0])) {
				arraySize = cal.get(Calendar.MONTH) + 1;
			} else {
				arraySize = 12;
			}

		} else {
			timeNums = getDaysOfMonth(vo.getTime());
			unit = "number";
			int month = cal.get(Calendar.MONTH) + 1;

			if (Integer.parseInt(vo.getTime().split("-")[1]) == month) {
				arraySize = cal.get(Calendar.DATE);
			} else {
				cal.set(Integer.parseInt(vo.getTime().split("-")[0]), Integer.parseInt(vo.getTime().split("-")[1]) - 1,
						1);
				int maxNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				arraySize = maxNum;
			}
		}

		// 每日收益
		List<CountStatsBO> list = statsService.findDailyIncome(vo);
		List<CountStatsBO> currList = statsService.findIncomeByStats(vo);

		String timeStr[] = new String[timeNums];
		String numStr[] = new String[arraySize];
		String numStr2[] = new String[arraySize];

		for (int j = 0; j < arraySize; j++) {
			numStr[j] = "0";
			numStr2[j] = "0";
		}
		for (int i = 0; i < timeNums; i++) {
			// numStr[i] = "0";
			// numStr2[i] = "0";
			timeStr[i] = "'" + (i + 1) + unit + "'";
		}

		if (list != null && list.size() > 0) {
			list.forEach(e -> {
				for (int i = 1; i <= arraySize; i++) {
					if (Integer.parseInt(e.getStatsTime()) == i) {
						numStr[i - 1] = e.getDeposit().toString();
					}
				}
			});
		}

		if (null != currList && currList.size() > 0) {
			currList.forEach(e -> {
				for (int i = 1; i <= arraySize; i++) {
					if (Integer.parseInt(e.getStatsTime()) == i) {
						numStr2[i - 1] = e.getDeposit().toString();
					}
				}
			});
		}

		SysSetting sysSetting = sysSettingDao.findOne();
		model.addAttribute("currency", sysSetting.getPlayformDefaultCurrency());

		model.addAttribute("vo", vo);
		model.addAttribute("timeStr", Arrays.toString(timeStr));
		model.addAttribute("numStr", Arrays.toString(numStr));
		model.addAttribute("numStr2", Arrays.toString(numStr2));

		return "modules/stats/platformIncomeStats";
	}

	public static void main(String[] args) {

		// Calendar cal = Calendar.getInstance();
		//
		// System.out.println(cal.get(Calendar.DATE));
		//
		// System.out.println(cal.get(Calendar.MONTH) + 1);

		String s = "2018-01";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(s.split("-")[0]), Integer.parseInt(s.split("-")[1]) - 1, 1);
		int maxNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(maxNum);

		System.out.println(calendar.get(Calendar.YEAR));
	}

}
