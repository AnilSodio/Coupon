/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.dao.AdminDao;
import vc.thinker.cabbage.user.vo.AdminFindPageVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.dao.OfficeDao;
import vc.thinker.sys.dao.RoleDao;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.dao.UserDao;
import vc.thinker.sys.dao.UserOfficeDao;
import vc.thinker.sys.dao.UserRoleDao;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.User;
import vc.thinker.sys.model.UserOffice;
import vc.thinker.sys.model.UserRole;
import vc.thinker.sys.service.SystemService;
import vc.thinker.sys.service.UserAccountService;
import vc.thinker.sys.utils.AdminUtils;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 * @version 2013-5-15
 */
@Service
@Transactional(readOnly = true)
public class AdminService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserOfficeDao userOfficeDao;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserAccountService accountService;
	
	@Autowired
	private OfficeDao officeDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	@Autowired
	private UserAccountDao accountDao;
	
	@Transactional(readOnly=false)
	public void freezeUser(Long userId) 
	{
		adminDao.freezeUser(userId);
	}

	public AdminBO findById(Long id) {
		return adminDao.getBOById(id);
	}
	
	public List<OfficeBO> findOffice(AdminBO admin){
		
		List<OfficeBO> officeList = new ArrayList<OfficeBO>(); 
		if (AdminUtils.isAdmin(admin.getUid())){
			officeList = officeDao.findAll();
		}else{
			officeList = officeDao.findAllChild(admin.getOffice().getId(), "%,"+admin.getOffice().getId()+",%");
		}
		return officeList;
	}
	
	public List<AdminBO> findUserByRole(String roleId){
		return adminDao.findByRole(roleId);
	}
	
	public List<AdminBO> findByOffice(Long officeId) {
		return adminDao.findByOffice(officeId);
	}
	
	public MyPage<AdminBO> findUser(MyPage<AdminBO> page, AdminFindPageVO vo) {
		//user.setOfficeId(currentUser.getOfficeId());
		adminDao.findByPage(page, vo);
		
		//注入机构名称
//		mappingHandle.mappinHandle(vo);
		return page;
	}
	

	@Transactional(readOnly = false)
	public void save(AdminBO user,List<RoleBO> roles,String loginName,String password,String[] officeIdList,String ip,User currentUser) {
		//更新时，先删除用户的角色、用户的数据范围
		if(user.getUid() != null){
			userRoleDao.deleteByUserId(user.getUid());
			userOfficeDao.deleteByUserId(user.getUid());
		}
		
		Long uid=user.getUid();
		if(uid == null){
			uid=systemService.createUser(SysUserContant.ACCOUNT_TYPE_1, user.getCompany().getId(),user.getOffice().getId(),user.getDataScope(),loginName, password, ip,null,currentUser);
		}else{
			if(StringUtils.isNotBlank(password)){
				accountService.passwordUpdate(uid, password);
			}
		}
		
		user.setUid(uid);
		user.setUserType("1");
		user.setIsDel(false);
		adminDao.save(user,currentUser);
		
		User up_user = new User();
		up_user.setId(user.getUid());
		up_user.setCompanyId(user.getCompanyId());
		up_user.setOfficeId(user.getOfficeId());
		up_user.setUpdateTime(new Date());
		
		userDao.save(up_user);
		
		//添加用户角色
		if(roles != null && roles.size() > 0){
			UserRole userRole=null;
			for (Role role : roles) {
				userRole=new UserRole();
				userRole.setRoleId(role.getId());
				userRole.setUserId(user.getUid());
				userRoleDao.save(userRole);
			}
		}
		
		//修改用户名
		if(!"thinkgem".equals(loginName)) {
			UserAccountBO acc_bo = new UserAccountBO();
			List<UserAccountBO> user_acc_list = accountDao.findByUid(uid);
			if(null != user_acc_list && user_acc_list.size() > 0) {
				acc_bo.setId(user_acc_list.get(0).getId());
				acc_bo.setLoginName(loginName);
				accountDao.save(acc_bo);
			}
		}
		//添加用户数据范围
		if(officeIdList != null && officeIdList.length > 0){
			UserOffice userOffice=null;
			for (String officeId : officeIdList) {
				userOffice=new UserOffice();
				userOffice.setOfficeId(Long.valueOf(officeId));
				userOffice.setUserId(user.getUid());
				userOfficeDao.save(userOffice);
			}
		}
		
		clearUserCached(String.valueOf(user.getUid()));
		
	}
	
	@Transactional(readOnly = false)
	public void save(AdminBO user,User currentUser) {
		adminDao.save(user,currentUser);
	}
	
	/**
	 * 清理缓存和其它相关
	 * @param userId
	 */
	private void clearUserCached(String userId){
		// 同步到Activiti
//		saveActiviti(userId);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		adminDao.delete(id);
		userDao.delete(id);
		accountDao.deleteByUid(id);
		userRoleDao.deleteByUserId(id);
		userOfficeDao.deleteByUserId(id);
		// 同步到Activiti
//		deleteActiviti(userDao.get(id));
	}
	
	//-- Role Service --//
	public RoleBO getRole(Long id) {
		return roleDao.getBO(id);
	}

	public Role findRoleByName(String name) {
		return roleDao.findByName(name);
	}
	
	@Transactional(readOnly = false)
	public void saveSetting(SysSetting bo) {
		sysSettingDao.save(bo);
	}
	
}
