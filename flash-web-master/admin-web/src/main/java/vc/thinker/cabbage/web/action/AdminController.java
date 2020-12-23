/**
f * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.sinco.common.security.PasswordUtil;
import com.sinco.common.utils.IPUtil;
import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.service.AdminService;
import vc.thinker.cabbage.user.service.UserService;
import vc.thinker.cabbage.user.vo.AdminFindPageVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.core.web.security.SessionAuthorizingRealm;
import vc.thinker.lbs.LbsBiz;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.User;
import vc.thinker.sys.service.OfficeService;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;
import vc.thinker.sys.service.UserOfficeService;
import vc.thinker.sys.utils.AdminUtils;
import vc.thinker.web.utils.UserUtils;

/**
 * 用户Controller
 * 
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class AdminController extends BaseController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private UserAccountService accountService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private OfficeService officeService;

	@Autowired
	private UserOfficeService userOfficeService;

	@Autowired
	private SessionAuthorizingRealm systemRealm;

	@Autowired
	private LbsBiz lbsBiz;

	@Autowired
	private UserService userService;

	@Value("${adminPath}")
	private String adminPath;

	@ModelAttribute("user")
	public AdminBO get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return adminService.findById(id);
		} else {
			return new AdminBO();
		}
	}

	@RequiresPermissions("sys:user:view")
	@SecurityMapping(name = "role.list", permGroup = "role.platUser", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "list" })
	public String list(AdminFindPageVO user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setIsAdmin(AdminUtils.isAdmin(UserUtils.getUser().getId()));
		MyPage<AdminBO> page = adminService.findUser(new MyPage<AdminBO>(request, response), user);
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return "modules/sys/userList";
	}

	@RequiresPermissions("sys:user:view")
	@SecurityMapping(name = "role.list", permGroup = "role.platUser", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping({ "userIndex", "" })
	public String userIndex() {
		return "modules/sys/userIndex";
	}

	@RequiresPermissions("sys:user:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.platUser", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("form")
	public String form(@ModelAttribute("user") AdminBO user, Model model) {
		// 判断显示的用户是否在授权范围内
		User currentUser = UserUtils.getUser();
//		if (!currentUser.isAdmin()) {
//					String dataScope = systemService.getDataScope(currentUser);
//					// System.out.println(dataScope);
//					if (dataScope.indexOf("office.id=") != -1) {
//						String AuthorizedOfficeId = dataScope.substring(dataScope.indexOf("office.id=") + 10, dataScope.indexOf(" or"));
//						if (!AuthorizedOfficeId.equalsIgnoreCase(officeId)) {
//							return "error/403";
//						}
//					}
//		}
//		if (user.getCompany() == null) {
//			user.setCompany(officeService.get(UserUtils.getUser().getCompanyId()));
//		}
//		if (user.getOffice() == null) {
//			user.setOffice(officeService.get(UserUtils.getUser().getOfficeId()));
//		}
		if (null != user && user.getUid() != null) {
			// 用户基本信息
			User userInfo = systemService.getUser(user.getUid());
			// 设置用户公司
			user.setCompany(officeService.get(userInfo.getCompanyId()));
			// 设置用户部门
			user.setOffice(officeService.get(userInfo.getOfficeId()));
			// 设置用户账号信息
			user.setUserAccount(accountService.findByUid(user.getUid(), SysUserContant.ACCOUNT_TYPE_1));
			// 设置用户角色信息
			user.setRoleList(systemService.findRoleByUser(user.getUid()));
			// 设置用户数据来源
			user.setDataScope(userInfo.getDataScope());
			user.setOfficeList(userOfficeService.findByUser(user.getUid()));
		} else {
			user.setCompany(officeService.get(UserUtils.getUser().getCompanyId()));
			user.setOffice(officeService.get(UserUtils.getUser().getOfficeId()));
		}

		model.addAttribute("officeList", officeService.findAll(currentUser));
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findRoleList(currentUser.getId()));
		return "modules/sys/userForm";
	}

	@RequiresPermissions("sys:user:modify")
	@SecurityMapping(name = "role.edit", permGroup = "role.platUser", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(AdminBO user, String loginName, String newPassword, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {

		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(Long.valueOf(request.getParameter("companyId"))));
		user.setOffice(new Office(Long.valueOf(request.getParameter("officeId"))));

		if (!beanValidator(model, user)) {
			return form(user, model);
		}

		// 角色数据有效性验证，过滤不在授权内的角色
		List<RoleBO> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
//		for (RoleBO r : UserUtils.getRoleList()) {
//			if (roleIdList.contains(r.getId())) {
//				roleList.add(r);
//			}
//		}
		RoleBO roleBO = null;
		for (String roleId : roleIdList) {
			roleBO = new RoleBO();
			roleBO.setId(Long.valueOf(roleId));
			roleList.add(roleBO);
		}
		user.setRoleList(roleList);

		String[] officeIdList = null;
		String officeIds = user.getOfficeIds();
		if (StringUtils.isNotEmpty(officeIds)) {
			officeIdList = officeIds.split(",");
		}

		// 当前用户
		User currentUser = UserUtils.getUser();

		// 保存用户信息
		adminService.save(user, user.getRoleList(), loginName, newPassword, officeIdList, IPUtil.getIpAddr(request),
				currentUser);

		// session缓存
		systemRealm.clearAllCachedAuthorizationInfo();

		// 清除当前用户缓存
		if (UserUtils.getPrincipal().getLoginName().equals(loginName)) {
			UserUtils.clearCacheMap();
		}

		addMessage(redirectAttributes, "保存用户'" + loginName + "'成功");
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	@RequiresPermissions("sys:user:delete")
	@SecurityMapping(name = "role.delete", permGroup = "role.platUser", userType = SysUserContant.USER_TYPE_1)
	@RequestMapping("delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {

		if (UserUtils.getUser().getId().equals(id)) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		} else if (AdminUtils.isAdmin(id)) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		} else {
//			systemService.deleteUser(id);
			userService.deletePlatformUser(id);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

//	@RequiresPermissions("sys:user:view")
//    @RequestMapping(value = "export", method=RequestMethod.POST)
//    public String exportFile(User user, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//			String fileName = "用户数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
//    		MyPage<UserBO> page = systemService.findUser(new MyPage<UserBO>(request, response, -1), user); 
//    		new ExportExcel("用户数据", User.class).setDataList(page.getList()).write(response, fileName).dispose();
//    		return null;
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
//		}
//		return "redirect:" + Global.getAdminPath() + "/sys/user/?repage";
//    }

//	@RequiresPermissions("sys:user:view")
//    @RequestMapping(value = "import", method=RequestMethod.POST)
//    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
//		if(Global.isDemoMode()){
//			addMessage(redirectAttributes, "演示模式，不允许操作！");
//			return "redirect:" + Global.getAdminPath() + "/sys/user/?repage";
//		}
//		
//		try {
//			int successNum = 0;
//			int failureNum = 0;
//			StringBuilder failureMsg = new StringBuilder();
//			ImportExcel ei = new ImportExcel(file.getName(),file.getInputStream(), 1, 0);
//			List<User> list = ei.getDataList(User.class);
//			for (User user : list){
//				try{
//					if ("true".equals(checkLoginName("", user.getLoginName()))){
//						user.setPassword(SystemService.entryptPassword("123456"));
//						BeanValidators.validateWithException(validator, user);
//						systemService.saveUser(user);
//						successNum++;
//					}else{
//						failureMsg.append("<br/>登录名 " + user.getLoginName() + " 已存在; ");
//						failureNum++;
//					}
//				}catch(ConstraintViolationException ex){
//					failureMsg.append("<br/>登录名 " + user.getLoginName() + " 导入失败：");
//					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//					for (String message : messageList){
//						failureMsg.append(message+"; ");
//						failureNum++;
//					}
//				}catch (Exception ex) {
//					failureMsg.append("<br/>登录名 " + user.getLoginName() + " 导入失败：" + ex.getMessage());
//				}
//			}
//			if (failureNum>0){
//				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
//			}
//			systemRealm.clearAllCachedAuthorizationInfo();
//			addMessage(redirectAttributes, "已成功导入 " + successNum+" 条用户" + failureMsg);
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导入用户失败！失败信息：" + e.getMessage());
//		}
//		return "redirect:" + Global.getAdminPath() + "/sys/user/?repage";
//    }

//	@RequiresPermissions("sys:user:view")
//    @RequestMapping("import/template")
//    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
//		try {
//			String fileName = "用户数据导入模板.xlsx";
//			List<User> list = Lists.newArrayList();
//			list.add(UserUtils.getUser());
//			new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
//			return null;
//		} catch (Exception e) {
//			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
//		}
//		return "redirect:" + Global.getAdminPath() + "/sys/user/?repage";
//    }

	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping("checkLoginName")
	public boolean checkLoginName(String id, String loginName) {
		if (accountService.checkLoginName(loginName, StringUtils.isNotEmpty(id) ? Long.valueOf(id) : 0L) == null) {
			return true;
		}
		return false;
	}

	@RequiresUser
	@RequestMapping("info")
	public String info(AdminBO user, Model model) {
		User currentUser = UserUtils.getUser();
		AdminBO admin = adminService.findById(currentUser.getId());
		if (currentUser.getCompanyId() != null) {
			admin.setCompany(officeService.get(currentUser.getCompanyId()));
		}
		if (currentUser.getOfficeId() != null) {
			admin.setOffice(officeService.get(currentUser.getOfficeId()));
		}
		// 用户基本信息
		User userInfo = systemService.getUser(currentUser.getId());
		// 设置用户账号信息
		admin.setUserAccount(accountService.findByUid(currentUser.getId(), SysUserContant.ACCOUNT_TYPE_1));
		// 设置用户角色信息
		admin.setRoleList(systemService.findRoleByUser(currentUser.getId()));
		// 设置用户数据来源
		admin.setDataScope(userInfo.getDataScope());
		admin.setOfficeList(userOfficeService.findByUser(currentUser.getId()));
		model.addAttribute("user", admin);
		return "modules/sys/userInfo";
	}

	@RequiresUser
	@RequestMapping("saveInfo")
	public String saveInfo(AdminBO user, Model model) {
		User currentUser = UserUtils.getUser(true);
		AdminBO admin = adminService.findById(currentUser.getId());
		admin.setName(user.getName());
		admin.setEmail(user.getEmail());
		admin.setPhone(user.getPhone());
		admin.setMobile(user.getMobile());
		admin.setRemarks(user.getRemarks());
		adminService.save(admin, currentUser);
		model.addAttribute("message", "保存用户信息成功");

		return "redirect:" + adminPath + "/sys/user/info";
	}

	@RequiresUser
	@RequestMapping("modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		UserAccountBO account = accountService.findByUid(user.getId(), SysUserContant.ACCOUNT_TYPE_1);
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
			if (PasswordUtil.validatePassword(oldPassword, account.getPassword())) {
				accountService.passwordUpdate(user.getId(), newPassword);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}
}
