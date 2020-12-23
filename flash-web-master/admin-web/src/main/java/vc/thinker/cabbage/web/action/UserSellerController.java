package vc.thinker.cabbage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.sinco.common.utils.IPUtil;

import cn.jiguang.common.utils.StringUtils;
import vc.thinker.cabbage.common.QRCodeUtil;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.service.CountryService;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.service.RefereeService;
import vc.thinker.cabbage.user.service.SellerService;
import vc.thinker.cabbage.user.service.UniqueRadomCodeService;
import vc.thinker.cabbage.user.vo.SellerVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.dao.UserAccountDao;

@Controller
@RequestMapping("${adminPath}/sys/seller")
public class UserSellerController {

	@Value("${adminPath}")
	private String adminPath;
	
	@Value("${google.js.key}")
	private String googleJsKye;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private UniqueRadomCodeService uniqueRadomCodeService; 
	
	@Autowired
	private RefereeService refereeService;
	
	@Autowired
	private CountryService countryService;
	
	
	@RequiresPermissions("sys:seller:list")
	@SecurityMapping(name="role.list",permGroup="role.merchant",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("list")
	public String list(Model model,MyPage<SellerBO> page,SellerVO vo){
		
		sellerService.findPageByVo(page,vo);
		model.addAttribute("page",page);
		model.addAttribute("vo",vo);
		model.addAttribute("countries",countryService.findAll());
		return "modules/cus/seller_list";
	}
	
	
	@RequiresPermissions("sys:seller:modify")
	@SecurityMapping(name="role.edit",permGroup="role.merchant",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("modify")
	public String modify(Model model,Long uid){
		List<CountryBO> countries = countryService.findAll();
		
		SellerBO info = null;
		if(null != uid){
			info = sellerService.findOne(uid);
			if(null != info.getRefereeUid()){
				RefereeBO referee = refereeService.findById(info.getRefereeUid());
				info.setRefereeName(referee.getRefereeName());
			}
		}
		if(null == info){
			info = new SellerBO();
		}
		
		List<RefereeBO> referee_list = refereeService.findAll();
		model.addAttribute("referee_list",referee_list);
		model.addAttribute("info",info);
		model.addAttribute("countries",countries);
		
		return "modules/cus/seller_modify";
	}
	
	@RequiresPermissions("sys:seller:modify")
	@SecurityMapping(name="role.edit",permGroup="role.merchant",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(Seller seller,HttpServletRequest request,String sellerCover1,String sellerCover2,String sellerCover3,String sellerCover4,String sellerCover5){
		
		JSONArray array = new JSONArray();
		
		if(!StringUtils.isEmpty(sellerCover1)){
			array.add(sellerCover1);
		}
		if(!StringUtils.isEmpty(sellerCover2)){
			array.add(sellerCover2);
		}
		if(!StringUtils.isEmpty(sellerCover3)){
			array.add(sellerCover3);
		}
		if(!StringUtils.isEmpty(sellerCover4)){
			array.add(sellerCover4);
		}
		if(!StringUtils.isEmpty(sellerCover5)){
			array.add(sellerCover5);
		}
		
		seller.setSellerCover(array.toString());
		String ip=IPUtil.getIpAddr(request);
		
		sellerService.saveOrUpdate(seller,ip);
		
		return "redirect:" + adminPath + "/sys/seller/list";
	}
	
	@RequestMapping("selectLonAndLat")
	public String selectLonAndLat(Model model) {
		
		model.addAttribute("googleJsKye",googleJsKye);
		return "modules/cus/selectLatAndLng";
	}
	
	@RequestMapping("checkSellerName")
	@ResponseBody
	public Boolean checkSellerName(Long uid,String sellerName){
		
		return sellerService.checkSellerName(uid,sellerName);
	}
	
	@RequestMapping("checkContactMobile")
	@ResponseBody
	public Boolean checkContactMobile(Long uid,String contactMobile){
		
		return sellerService.checkContactMobile(uid,contactMobile);
	}
	
	
	
	@RequiresPermissions("sys:seller:modify")
	@SecurityMapping(name="role.edit",permGroup="role.merchant",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("disAndEnable")
	@ResponseBody
	public String disAndEnable(Long uid,int status){
		
		Seller seller = new Seller();
		seller.setUid(uid);
		seller.setStatus(status);
		
		sellerService.saveOrUpdate(seller,"");
		
		return "0000";
	}
	
	
	@RequiresPermissions("sys:seller:detail")
	@SecurityMapping(name="role.detail",permGroup="role.merchant",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("detail")
	public String detail(Model model,Long uid){
		
		SellerBO info = sellerService.findOne(uid);
		
		if(null != info.getRefereeUid()){
			RefereeBO referee = refereeService.findOne(info.getRefereeUid());
			model.addAttribute("referee",referee);
		}
		
		String inviteCode = info.getInviteCode();
		
		if(StringUtils.isEmpty(inviteCode)){
			inviteCode = uniqueRadomCodeService.getCode(CommonConstants.CODE_TYPE_INVITION);
			SellerBO up_bo = new SellerBO();
			up_bo.setUid(uid);
			up_bo.setInviteCode(inviteCode);
			sellerService.saveOrUpdate(up_bo, null);
			
			info.setInviteCode(inviteCode);
		}
		
		String twoCode = QRCodeUtil.generateToBase64(inviteCode, 200, 200);
		
		info.setTwoCode(twoCode);
		model.addAttribute("info",info);
		model.addAttribute("countries",countryService.findAll());
		
		return "modules/cus/seller_detail";
	}
	
	@RequestMapping("checkEmail")
	@ResponseBody
	public Boolean checkEmail(Long uid,String email){
		
		return sellerService.checkEmail(uid,email);
	}
	
	@RequestMapping("findOne")
	@ResponseBody
	public SellerBO findOne(Long sellerId){
		SellerBO seller = sellerService.findOne(sellerId);
		return seller;
	}
}
