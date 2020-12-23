package vc.thinker.cabbage.web.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.util.StringUtils;
import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.dao.DepositPayLogDao;
import vc.thinker.cabbage.user.service.MemberService;
import vc.thinker.cabbage.user.service.UserDepositLogService;
import vc.thinker.cabbage.user.vo.DepositPayLogVO;
import vc.thinker.cabbage.user.vo.MemberVO;
import vc.thinker.cabbage.user.vo.UserDepositLogVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.pay.response.RefundResponse;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.web.utils.UserUtils;

@Controller
@RequestMapping("${adminPath}/sys/money")
public class DepositController {

//	private static Logger logger = LoggerFactory.getLogger(DepositController.class);
	
	@Value("${adminPath}")
	private String adminPath;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private UserDepositLogService userDepositService;
	
	@Autowired
	private DepositPayLogDao depositPayLogDao;
	
	@RequiresPermissions("sys:money:list")
	@SecurityMapping(name="financial.userDeposit",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(@ModelAttribute("page") MyPage<MemberBO> page,Model model,MemberVO vo) {
		
		memberService.findByPage(page, vo);
		
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		
		return "modules/money/moneyList";
	}
	
	@RequiresPermissions("sys:money:check")
	@SecurityMapping(name="financial.refundReview",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("check")
	public String check(@ModelAttribute("page") MyPage<DepositPayLogBO> page,Model model,DepositPayLogVO vo) {
		
		depositPayLogDao.findPageByVo(page,vo);
		
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		
		return "modules/money/checkList";
		
	}
	
	@RequiresPermissions("sys:money:refund")
	@SecurityMapping(name="refund",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("refund")
	@ResponseBody
	public String refund(Long id, String refundRemark, String refundAccount) {
		
		DepositPayLogBO info = depositPayLogDao.findOne(id);
		
		if(StringUtils.isEmpty(info.getOutOrderId())){
			return "信息有误，可联系后台开发人员";
		}
		
		RefundResponse response = memberService.refund(info, UserUtils.getUser().getId(), refundRemark);
		
		if(!response.isSuccess()) {
			return "1";
		}
		
		return response.getMsg();
		
	}
	@RequiresPermissions("sys:money:refund")
	@SecurityMapping(name="role.refund",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("refundForm")
	public String refundFrom(Long id, Model model) {
		model.addAttribute("id", id);
		return "modules/money/refundForm";
	}
	
	
	@RequiresPermissions("sys:money:refund")
	@SecurityMapping(name="role.refund",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("checkRedirect")
	public String checkRedirect(Long id,Model model){
		
		DepositPayLogBO info = depositPayLogDao.findOne(id);
		model.addAttribute("info",info);
		
		return "modules/money/checkForm";
	}
	
//	@RequiresPermissions("sys:money:list")
//	@SecurityMapping(name="list",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("export")
//	public String export(HttpServletResponse response,MemberVO vo) {
//		
//		//查询要导出的数据
//		List<MemberBO> mem_list = memberService.findNoPayByPage(null,vo);
//		
//		OutputStream os = null;
//		
//		String fileName = DateTimeUtils.getDateTimeMils();
//		
//		try
//	      {
//	        os = response.getOutputStream();
//	      } catch (IOException e) {
//	        e.printStackTrace();
//	      }
//		response.reset();
//	    response.setContentType("UTF-8");
//	    response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
//	    response.setDateHeader("Expires", -1L);
//	    response.setContentType("application/msexcel");
//	    
//	    try {
//	        exportToExcel(os, mem_list, fileName);
//	      } catch (Exception e) {
//	        e.printStackTrace();
//	      }
//		return null;
//	}
	
	@RequiresPermissions("sys:money:list")
	@SecurityMapping(name="role.detail",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("details")
	public String details(Model model,MemberBO bo,@ModelAttribute("page") MyPage<UserDepositLogBO> page) {
		
		//查询用户的基本信息
		MemberBO info = memberService.findOne(bo.getUid());
		
		//查询用户的押金操作记录
		UserDepositLogVO vo = new UserDepositLogVO();
		vo.setUid(bo.getUid());
		userDepositService.selectPageByUid(page,vo);
		
		model.addAttribute("info",info);
		model.addAttribute("page",page);
		
		return "modules/money/depositList";
	}
	
//	@RequiresPermissions("sys:money:list")
//	@SecurityMapping(name="list",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
//	@RequestMapping("detailExport")
//	public String detailExport(UserDepositLogVO vo,HttpServletResponse response) {
//		
//		//查询要打印的数据
//		List<UserDepositLogBO> export_info = userDepositService.selectPageByUid(null, vo);
//		
//		OutputStream os = null;
//		
//		String fileName = DateTimeUtils.getDateTimeMils();
//		
//		try
//	      {
//	        os = response.getOutputStream();
//	      } catch (IOException e) {
//	        e.printStackTrace();
//	      }
//		response.reset();
//	    response.setContentType("UTF-8");
//	    response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
//	    response.setDateHeader("Expires", -1L);
//	    response.setContentType("application/msexcel");
//	    
//	    try {
//	        exportDetails(os, export_info, fileName);
//	      } catch (Exception e) {
//	        e.printStackTrace();
//	      }
//	    
//		return null;
//	}
	
	
	/**
	 * 导出明细
	 * @param os 输入流
	 * @param export_info 待导出的数据
	 * @param fileName 文件名
	 */
//	private void exportDetails(OutputStream os, List<UserDepositLogBO> export_info, String fileName) {
//		
//		try{
//			HSSFWorkbook workbook = new HSSFWorkbook();
//		    
//		    HSSFFont columnHeadFont = workbook.createFont();
//		    columnHeadFont.setFontName("黑体");
//		    columnHeadFont.setFontHeightInPoints((short) 10);
//		    columnHeadFont.setBoldweight((short) 700);
//		    
//		    HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
//		    columnHeadStyle.setFont(columnHeadFont);
//		    columnHeadStyle.setAlignment((short) 2);
//		    columnHeadStyle.setVerticalAlignment((short) 1);
//		    columnHeadStyle.setLocked(true);
//		    columnHeadStyle.setWrapText(true);
//		    columnHeadStyle.setLeftBorderColor((short)8);
//		    columnHeadStyle.setBorderLeft((short)1);
//		    columnHeadStyle.setRightBorderColor((short)8);
//		    columnHeadStyle.setBorderRight((short)1);
//		    columnHeadStyle.setTopBorderColor((short)8);
//		    columnHeadStyle.setBorderTop((short)1);
//		    columnHeadStyle.setBorderBottom((short)1);
//		    columnHeadStyle.setBottomBorderColor((short)8);
//		    columnHeadStyle.setFillForegroundColor((short)9);
//		    
//		    HSSFSheet sheet = workbook.createSheet(fileName);
//		    HSSFRow rowHeader = sheet.createRow(0);
//		    rowHeader.setHeight((short) 600);
//		    
//		    HSSFCell cell0 = rowHeader.createCell(0);
//		    cell0.setCellStyle(columnHeadStyle);
//		    cell0.setCellValue("类型");
//		    
//		    HSSFCell cell1 = rowHeader.createCell(1);
//		    cell1.setCellStyle(columnHeadStyle);
//		    cell1.setCellValue("金额");
//		    
//		    HSSFCell cell2 = rowHeader.createCell(2);
//		    cell2.setCellStyle(columnHeadStyle);
//		    cell2.setCellValue("时间");
//		    
//		    HSSFFont font = workbook.createFont();
//		    font.setFontName("宋体");
//		    font.setFontHeightInPoints((short) 10);
//		    
//		    HSSFCellStyle style = workbook.createCellStyle();
//		    style.setFont(font);
//		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		    style.setVerticalAlignment((short) 0);
//		    style.setWrapText(true);
//		    style.setLeftBorderColor((short) 8);
//		    style.setBorderLeft((short) 1);
//		    style.setRightBorderColor((short) 8);
//		    style.setBorderRight((short) 1);
//		    style.setBorderBottom((short) 1);
//		    style.setBottomBorderColor((short) 8);
//		    style.setFillForegroundColor((short) 9);
//		    style.setWrapText(true);
//		    
//		    sheet.autoSizeColumn(0);
//		    sheet.autoSizeColumn(1);
//		    sheet.autoSizeColumn(2);
//		    
//		    sheet.setColumnWidth(0, 5190);
//		    sheet.setColumnWidth(1, 5190);
//		    sheet.setColumnWidth(2, 5190);
//		    
//		    for (int i = 0; i < export_info.size(); i++) {
//		    	UserDepositLogBO bo = export_info.get(i);
//		    	HSSFRow row = sheet.createRow(i + 1);
//		    	HSSFCell cells0 = row.createCell(0);
//		    	String type = " ";
//		    	if(null != bo.getType() && !"".equals(bo.getType())){
//		    		if("1".equals(bo.getType())){
//		    			type = "支付";
//		    		}else if("2".equals(bo.getType())){
//		    			type = "审核中";
//		    		}else if("3".equals(bo.getType())){
//		    			type = "已退款";
//		    		}
//		    	}
//		    	cells0.setCellValue(type);
//		    	cells0.setCellStyle(style);
//		    	
//		    	HSSFCell cells1 = row.createCell(1);
//		    	cells1.setCellValue(bo.getAmount()+"");
//		    	cells1.setCellStyle(style);
//		    	
//		    	HSSFCell cells2 = row.createCell(2);
//		    	cells2.setCellValue(DateTimeUtils.dateFormatString(bo.getCreateTime()));
//		    	cells2.setCellStyle(style);
//		    	
//		    }
//		    workbook.write(os);
//		    
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	/**
	 * 导出列表 
	 * @param os 输入流
	 * @param listCorpCust 待导出的数据
	 * @param fileName 文件名
	 * @throws Exception
	 */
//	private void exportToExcel(OutputStream os, List<MemberBO> listCorpCust, String fileName) throws Exception {
//		
//		try{
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			
//		    HSSFFont headfont = workbook.createFont();
//		    headfont.setFontName("黑体");
//		    headfont.setFontHeightInPoints((short) 10);
//		    headfont.setBoldweight((short)700);
//		    
//		    HSSFCellStyle headstyle = workbook.createCellStyle();
//		    headstyle.setFont(headfont);
//		    headstyle.setAlignment((short)2);
//		    headstyle.setVerticalAlignment((short)1);
//		    headstyle.setBorderBottom((short)1);
//		    headstyle.setBorderTop((short)5);
//		    headstyle.setBorderLeft((short)5);
//		    headstyle.setBorderRight((short)5);
//		    
//		    HSSFFont columnHeadFont = workbook.createFont();
//		    columnHeadFont.setFontName("黑体");
//		    columnHeadFont.setFontHeightInPoints((short) 10);
//		    columnHeadFont.setBoldweight((short) 700);
//		    
//		    HSSFCellStyle columnHeadStyle = workbook.createCellStyle();
//		    columnHeadStyle.setFont(columnHeadFont);
//		    columnHeadStyle.setAlignment((short) 2);
//		    columnHeadStyle.setVerticalAlignment((short) 1);
//		    columnHeadStyle.setLocked(true);
//		    columnHeadStyle.setWrapText(true);
//		    columnHeadStyle.setLeftBorderColor((short)8);
//		    columnHeadStyle.setBorderLeft((short)1);
//		    columnHeadStyle.setRightBorderColor((short)8);
//		    columnHeadStyle.setBorderRight((short)1);
//		    columnHeadStyle.setTopBorderColor((short)8);
//		    columnHeadStyle.setBorderTop((short)1);
//		    columnHeadStyle.setBorderBottom((short)1);
//		    columnHeadStyle.setBottomBorderColor((short)8);
//		    columnHeadStyle.setFillForegroundColor((short)9);
//		    
//		    HSSFSheet sheet = workbook.createSheet(fileName);
//		    HSSFRow rowHeader = sheet.createRow(0);
//		    rowHeader.setHeight((short) 600);
//		    
//		    HSSFCell cell0 = rowHeader.createCell(0);
//		    cell0.setCellStyle(columnHeadStyle);
//		    cell0.setCellValue("昵称");
//		    
//		    HSSFCell cell1 = rowHeader.createCell(1);
//		    cell1.setCellStyle(columnHeadStyle);
//		    cell1.setCellValue("手机号");
//		    
//		    HSSFCell cell2 = rowHeader.createCell(2);
//		    cell2.setCellStyle(columnHeadStyle);
//		    cell2.setCellValue("金额");
//		    
//		    HSSFFont font = workbook.createFont();
//		    font.setFontName("宋体");
//		    font.setFontHeightInPoints((short) 10);
//		    
//		    HSSFCellStyle style = workbook.createCellStyle();
//		    style.setFont(font);
//		    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		    style.setVerticalAlignment((short) 0);
//		    style.setWrapText(true);
//		    style.setLeftBorderColor((short) 8);
//		    style.setBorderLeft((short) 1);
//		    style.setRightBorderColor((short) 8);
//		    style.setBorderRight((short) 1);
//		    style.setBorderBottom((short) 1);
//		    style.setBottomBorderColor((short) 8);
//		    style.setFillForegroundColor((short) 9);
//		    style.setWrapText(true);
//		    
//		    sheet.autoSizeColumn(0);
//		    sheet.autoSizeColumn(1);
//		    sheet.autoSizeColumn(2);
//		    
//		    sheet.setColumnWidth(0, 35*256);
//		    sheet.setColumnWidth(1, 5190);
//		    sheet.setColumnWidth(2, 3190);
//		    
//		    for (int i = 0; i < listCorpCust.size(); i++) {
//		    	MemberBO bo = listCorpCust.get(i);
//		    	HSSFRow row = sheet.createRow(i + 1);
//		    	HSSFCell cells0 = row.createCell(0);
//		    	cells0.setCellValue(bo.getNickname());
//		    	cells0.setCellStyle(style);
//		    	
//		    	HSSFCell cells1 = row.createCell(1);
//		    	cells1.setCellValue(bo.getMobile());
//		    	cells1.setCellStyle(style);
//		    	
//		    	HSSFCell cells2 = row.createCell(2);
//		    	cells2.setCellValue(bo.getDeposit()+"");
//		    	cells2.setCellStyle(style);
//		    	
//		    }
//		    workbook.write(os);
//		    
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	@RequiresPermissions("sys:money:list")
	@SecurityMapping(name="financial.depositRecord",permGroup="role.financial",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("log_list")
	public String log_list(@ModelAttribute("page") MyPage<DepositPayLogBO> page,Model model,DepositPayLogVO vo) {
		
		memberService.findloglist(page,vo);
		
		model.addAttribute("vo",vo);
		model.addAttribute("page",page);
		
		return "modules/money/deposit_log_list";
	}
	
}
