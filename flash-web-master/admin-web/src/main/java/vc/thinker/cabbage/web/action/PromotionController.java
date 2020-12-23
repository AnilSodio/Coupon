package vc.thinker.cabbage.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sinco.dic.client.DicContent;
import vc.thinker.cabbage.sys.bo.CouponBO;
import vc.thinker.cabbage.sys.bo.PromotionBO;
import vc.thinker.cabbage.sys.bo.PromotionTypeBO;
import vc.thinker.cabbage.sys.dao.PromotionTypeDao;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.sys.service.PromotionService;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.vo.MemberVO;
import vc.thinker.cabbage.user.vo.UserCouponVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.bo.DicAreaBO;
import vc.thinker.sys.contants.SysUserContant;

/**
 * 活动相关 controller
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/pro")
public class PromotionController {

	@Value("${adminPath}")
	private String adminPath;

	@Autowired
	private PromotionService promotionService;
	@Autowired
	private MemberService customerService;
	@Autowired
	private DicContent dicContent;

	@Autowired
	private PromotionTypeDao promotionTypeDao;
	
//	@Autowired
//	private CouponDao CouponDao;
	
	@Autowired
	private CountryService countryService;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// 注册自定义的属性编辑器
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		// 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@RequiresPermissions("sys:promotion:list")
	@SecurityMapping(name = "role.activity.list", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("listPro")
	public String listPromotion(@ModelAttribute("page") MyPage<PromotionBO> page, Model model) {
		promotionService.findPromotionPage(page);
		if (page.getContent() != null && page.getContent().size() > 0) {
			for (PromotionBO pro : page.getContent()) {
				getPromotionAreaLabel(pro);
			}
		}
		model.addAttribute("page", page);
		return "modules/promotion/promotionList";
	}

	private void getPromotionAreaLabel(PromotionBO pro) {
		String areaIds = pro.getAreaId();
		if (!StringUtils.isEmpty(areaIds)) {
			List<DicAreaBO> dicAreaList = dicContent.getDics(DicAreaBO.class, areaIds.split(";"));
			StringBuffer areaLabel = new StringBuffer();
			for (DicAreaBO dicArea : dicAreaList) {
				areaLabel.append(dicArea.getName());
				if (!dicArea.getCode().equals("1-11") && !dicArea.getCode().equals("1-12")) {
					areaLabel.append("(").append(dicContent.getDic(DicAreaBO.class, dicArea.getParentCode()).getName())
							.append(")").append("；");

				}
			}
			pro.setAreaLabel(areaLabel.toString());
		}
	}

	@RequiresPermissions("sys:promotion:modify")
	@SecurityMapping(name = "role.activity.edit", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("addPromotion")
	public String addPromotion(Long pid, Model model) {
		List<CouponBO> listCoupon = promotionService.findAllCoupon();
		List<PromotionTypeBO> listType = promotionService.findProType();
		model.addAttribute("listCoupon", listCoupon);
		model.addAttribute("listType", listType);
		if (pid != null) {
			PromotionBO promotion = promotionService.finPromotionById(pid);
			getPromotionAreaLabel(promotion);
			model.addAttribute("obj", promotion);
		} else {
			model.addAttribute("obj", new PromotionBO());
		}
		return "modules/promotion/promotionForm";
	}

	@RequiresPermissions("sys:promotion:modify")
	@SecurityMapping(name = "role.activity.list", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("savePromotion")
	public String savePromotion(PromotionBO bo, Model model) {
		bo.setCreateTime(new Date());
		bo.setIsDeleted(false);
		Long typeId = bo.getProTypeId();
		PromotionTypeBO typeBo = promotionTypeDao.findOne(typeId);
		bo.setProTypeCode(typeBo.getTypeCode());
		promotionService.savePromotion(bo);
		return "redirect:" + adminPath + "/sys/pro/listPro";
	}

	@RequiresPermissions("sys:promotion:delete")
	@SecurityMapping(name = "role.activity.delete", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("delPromotion")
	@ResponseBody
	public String delPromotion(Long pid, Model model) {
//		PromotionBO bo = new PromotionBO();
//		bo.setIsDeleted(true);
//		bo.setId(pid);
//		promotionService.savePromotion(bo);
		
		promotionService.delete(pid);
		
		return "0000";
		
//		return "redirect:" + adminPath + "/sys/pro/listPro";
	}

	// ================================================================================

	@RequiresPermissions("sys:coupon:list")
	@SecurityMapping(name = "role.coupon.list", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("listCoupon")
	public String listCoupon(@ModelAttribute("page") MyPage<CouponBO> page, Model model) {
		promotionService.findCouponPage(page);
		model.addAttribute("page", page);
		return "modules/promotion/couponList";
	}

	@RequiresPermissions("sys:coupon:sendlist")
	@SecurityMapping(name = "role.coupon.sendList", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("findCouponList")
	public String findCouponList(@ModelAttribute("page") MyPage<UserCouponBO> page, Model model, UserCouponVO vo) {
		
		promotionService.findCouponList(page, vo);
		
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "modules/promotion/sendCoupon";
	}

	@RequiresPermissions("sys:coupon:addCoupon")
	@SecurityMapping(name = "role.coupon.edit", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("addCoupon")
	public String addCoupon(Long cid, Model model) {
		if (cid != null) {
			CouponBO coupon = promotionService.finCouponById(cid);
			model.addAttribute("obj", coupon);
		} else {
			model.addAttribute("obj", new CouponBO());
		}
		
		model.addAttribute("currencies",countryService.findCurrencies());
		return "modules/promotion/couponForm";
	}
	
	@RequestMapping("checkName")
	@ResponseBody
	public Boolean checkName(Long id,String name){
		
		Boolean result = promotionService.checkName( id, name);
		
		return result;
	}

	@RequiresPermissions("sys:coupon:addCoupon")
	@SecurityMapping(name = "role.coupon.edit", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("saveCoupon")
	public String saveCoupon(CouponBO bo, Model model) {
		
		promotionService.saveCoupon(bo);
		return "redirect:" + adminPath + "/sys/pro/listCoupon";
	}

	@RequiresPermissions("sys:coupon:sendCoupon")
	@SecurityMapping(name = "role.coupon.send", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("sendCoupon")
	public String sendCoupon(@ModelAttribute("page") MyPage<MemberBO> page, Model model, Long cid, MemberVO vo) {
		page.setPageSize(10);
		
//		CouponBO coupon = CouponDao.findOne(cid);
//		if(null != coupon){
//			vo.setCurrency(coupon.getCurrency());
//		}
		customerService.findUnionInfoByPage(page, vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("cid", cid);
		return "modules/promotion/sendCouponForm";
	}

	@RequiresPermissions("sys:coupon:sendCoupon")
	@SecurityMapping(name = "role.coupon.send", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("saveSendCoupon")
	@ResponseBody
		public String saveSendCoupon(Long cid, String userIds, Model model,int num) {
		promotionService.sendCoupon(cid, userIds.split(";"),num);
		return "1";
	}

	@RequestMapping("sendNum")
	public String sendNun(){
		return "modules/promotion/sendNum";
	}
	
	@RequiresPermissions("sys:coupon:delCoupon")
	@SecurityMapping(name = "role.coupon.delete", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("delCoupon")
	@ResponseBody
	public String delCoupon(Long cid, Model model) {
		promotionService.deleteCoupe(cid);
		return "0000";
	}

	@RequiresUser
	@ResponseBody
	@RequestMapping("selectCityData")
	public List<Map<String, Object>> selectCityData(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");

		List<DicAreaBO> listCity = dicContent.getRootDics(DicAreaBO.class);

		List<Map<String, Object>> mapList = Lists.newArrayList();

		for (DicAreaBO province : listCity) {
			try {
				if (province.getChildren() != null && province.getChildren().size() > 0) {
					for (DicAreaBO cityBO : province.getChildren()) {
						Map<String, Object> map = Maps.newHashMap();
						map.put("id", cityBO.getCode());
						map.put("sequence", cityBO.getSequence());
						map.put("title", cityBO.getName() + "(" + province.getName() + ")");
						map.put("label", cityBO.getName() + "(" + province.getName() + ")");
						mapList.add(map);
					}
				} else if (province.getCode().equals("1-11") || province.getCode().equals("1-12")) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", province.getCode());
					map.put("sequence", province.getSequence());
					map.put("title", province.getName());
					map.put("label", province.getName());
					mapList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

		try {
			listSort(mapList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapList;
	}

	public void listSort(List<Map<String, Object>> resultList) throws Exception {
		// resultList是需要排序的list，其内放的是Map
		// 返回的结果集
		Collections.sort(resultList, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				// o1，o2是list中的Map，可以在其内取得值，按其排序，此例为升序，s1和s2是排序字段值
				Integer s1 = Integer.parseInt(o1.get("sequence").toString());
				Integer s2 = Integer.parseInt(o2.get("sequence").toString());
				return s1.compareTo(s2);
			}
		});

	}

//	@RequiresPermissions("sys:coupon:list")
//	@SecurityMapping(name = "coupon list", permGroup = "role.activity", userType = SysUserContant.USER_TYPE_1)
//	@RequestMapping("findCouponToExport")
//	public void findCouponToExport(HttpServletResponse response,UserCouponVO vo) {
//		
//		List<UserCouponBO> list = promotionService.findCouponList(null, vo);
//		OutputStream os = null;
//
//		String fileName = DateTimeUtils.getDateTimeMils();
//		try {
//			os = response.getOutputStream();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		response.reset();
//		response.setContentType("UTF-8");
//		response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
//		response.setDateHeader("Expires", -1L);
//		response.setContentType("application/msexcel");
//		try {
//			exportDetails(os, list, fileName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	private void exportDetails(OutputStream os, List<UserCouponBO> list, String fileName) {
//		try {
//			HSSFWorkbook workbook = new HSSFWorkbook();
//
//			HSSFFont headfont = workbook.createFont();
//			headfont.setFontName("黑体");
//			headfont.setFontHeightInPoints((short) 10);
//			headfont.setBoldweight((short) 700);
//
//			HSSFCellStyle headstyle = workbook.createCellStyle();
//			headstyle.setFont(headfont);
//			headstyle.setAlignment((short) 2);
//			headstyle.setVerticalAlignment((short) 1);
//			headstyle.setBorderBottom((short) 1);
//			headstyle.setBorderTop((short) 5);
//			headstyle.setBorderLeft((short) 5);
//			headstyle.setBorderRight((short) 5);
//
//			HSSFFont columnHeadFont = workbook.createFont();
//			columnHeadFont.setFontName("黑体");
//			columnHeadFont.setFontHeightInPoints((short) 10);
//			columnHeadFont.setBoldweight((short) 700);
//
//			HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
//			columnHeadStyle.setFont(columnHeadFont);
//			columnHeadStyle.setAlignment((short) 2);
//			columnHeadStyle.setVerticalAlignment((short) 1);
//			columnHeadStyle.setLocked(true);
//			columnHeadStyle.setWrapText(true);
//			columnHeadStyle.setLeftBorderColor((short) 8);
//			columnHeadStyle.setBorderLeft((short) 1);
//			columnHeadStyle.setRightBorderColor((short) 8);
//			columnHeadStyle.setBorderRight((short) 1);
//			columnHeadStyle.setTopBorderColor((short) 8);
//			columnHeadStyle.setBorderTop((short) 1);
//			columnHeadStyle.setBorderBottom((short) 1);
//			columnHeadStyle.setBottomBorderColor((short) 8);
//			columnHeadStyle.setFillForegroundColor((short) 9);
//
//			HSSFSheet sheet = workbook.createSheet(fileName);
//			HSSFRow rowHeader = sheet.createRow(0);
//			rowHeader.setHeight((short) 600);
//
//			HSSFCell cell0 = rowHeader.createCell(0);
//			cell0.setCellStyle(columnHeadStyle);
//			cell0.setCellValue("活动类型");
//
//			HSSFCell cell1 = rowHeader.createCell(1);
//			cell1.setCellStyle(columnHeadStyle);
//			cell1.setCellValue("昵称");
//
//			HSSFCell cell2 = rowHeader.createCell(2);
//			cell2.setCellStyle(columnHeadStyle);
//			cell2.setCellValue("手机号");
//
//			HSSFCell cell3 = rowHeader.createCell(3);
//			cell3.setCellStyle(columnHeadStyle);
//			cell3.setCellValue("地址");
//
//			HSSFCell cell4 = rowHeader.createCell(4);
//			cell4.setCellStyle(columnHeadStyle);
//			cell4.setCellValue("金额");
//
//			HSSFCell cell5 = rowHeader.createCell(5);
//			cell5.setCellStyle(columnHeadStyle);
//			cell5.setCellValue("状态");
//
//			HSSFCell cell6 = rowHeader.createCell(6);
//			cell6.setCellStyle(columnHeadStyle);
//			cell6.setCellValue("过期时间");
//
//			HSSFCell cell7 = rowHeader.createCell(7);
//			cell7.setCellStyle(columnHeadStyle);
//			cell7.setCellValue("发劵时间");
//
//			HSSFFont font = workbook.createFont();
//			font.setFontName("宋体");
//			font.setFontHeightInPoints((short) 10);
//
//			HSSFCellStyle style = workbook.createCellStyle();
//			style.setFont(font);
//			style.setAlignment((short) 1);
//			style.setVerticalAlignment((short) 0);
//			style.setWrapText(true);
//			style.setLeftBorderColor((short) 8);
//			style.setBorderLeft((short) 1);
//			style.setRightBorderColor((short) 8);
//			style.setBorderRight((short) 1);
//			style.setBorderBottom((short) 1);
//			style.setBottomBorderColor((short) 8);
//			style.setFillForegroundColor((short) 9);
//			style.setWrapText(true);
//
//			sheet.autoSizeColumn(0);
//			sheet.autoSizeColumn(1);
//			sheet.autoSizeColumn(2);
//			sheet.autoSizeColumn(3);
//			sheet.autoSizeColumn(4);
//			sheet.autoSizeColumn(5);
//			sheet.autoSizeColumn(6);
//			sheet.autoSizeColumn(7);
//
//			sheet.setColumnWidth(0, 3000);
//			sheet.setColumnWidth(1, 5000);
//			sheet.setColumnWidth(2, 4000);
//			sheet.setColumnWidth(3, 3000);
//			sheet.setColumnWidth(4, 5590);
//			sheet.setColumnWidth(5, 3000);
//			sheet.setColumnWidth(6, 11000);
//			sheet.setColumnWidth(7, 11000);
//
//			for (int i = 0; i < list.size(); i++) {
//				UserCouponBO bo = list.get(i);
//				HSSFRow row = sheet.createRow(i + 1);
//
//				HSSFCell cells0 = row.createCell(0);
//				cells0.setCellValue(bo.getSource());
//				cells0.setCellStyle(style);
//
//				HSSFCell cells1 = row.createCell(1);
//				cells1.setCellValue(bo.getNickname());
//				cells1.setCellStyle(style);
//
//				HSSFCell cells2 = row.createCell(2);
//				cells2.setCellValue(bo.getMobile());
//				cells2.setCellStyle(style);
//
//				HSSFCell cells3 = row.createCell(3);
//				cells3.setCellValue(bo.getCityName());
//				cells3.setCellStyle(style);
//
//				HSSFCell cells4 = row.createCell(4);
//				cells4.setCellValue(bo.getAmount().toString());
//				cells4.setCellStyle(style);
//
//				HSSFCell cells5 = row.createCell(5);
//				String status = "";
//				if (bo.getStatus() == 1) {
//					status = "未使用";
//				} else if (bo.getStatus() == 2) {
//					status = "已使用";
//				} else {
//					status = "";
//				}
//				cells5.setCellValue(status);
//				cells5.setCellStyle(style);
//
//				HSSFCell cells6 = row.createCell(6);
//				if (null != bo.getExpireDate()) {
//					cells6.setCellValue(DateTimeUtils.dateFormatString(bo.getExpireDate()));
//				} else {
//					cells6.setCellValue(" ");
//				}
//				cells6.setCellStyle(style);
//
//				HSSFCell cells7 = row.createCell(7);
//				if (null != bo.getCreateTime()) {
//					cells7.setCellValue(DateTimeUtils.dateFormatString(bo.getCreateTime()));
//				} else {
//					cells7.setCellValue(" ");
//				}
//				cells7.setCellStyle(style);
//			}
//			workbook.write(os);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
