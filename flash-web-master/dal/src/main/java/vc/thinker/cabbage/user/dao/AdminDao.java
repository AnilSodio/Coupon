/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.mapper.AdminMapper;
import vc.thinker.cabbage.user.model.Admin;
import vc.thinker.cabbage.user.model.AdminExample;
import vc.thinker.cabbage.user.vo.AdminFindPageVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.mapper.RoleMapper;
import vc.thinker.sys.mapper.UserRoleMapper;
import vc.thinker.sys.model.User;

/**
 * 用户DAO接口
 * 
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class AdminDao {

	@Autowired
	private AdminMapper mapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;

	public int findCountByUserType(String userType) {
		AdminExample example = new AdminExample();
		example.createCriteria().andUserTypeEqualTo(userType);
		return mapper.countByExample(example);
	}

	public List<AdminBO> findByRole(String roleId) {
		return mapper.findByRole(roleId);
	}

	public List<AdminBO> findByPage(MyPage<AdminBO> page, AdminFindPageVO vo) {
		List<AdminBO> list = mapper.findByPage(page,vo);
		if(null != list && list.size() > 0){
			for(AdminBO bo:list){
				bo.setRoleList(roleMapper.findUserRole(bo.getUid(),SysUserContant.USER_TYPE_1));
			}
		}
		return list;
	}

	public List<AdminBO> findAllList() {
		AdminExample example = new AdminExample();
		example.createCriteria().andIsDelEqualTo(false);
		example.setOrderByClause("id");
		return mapper.selectByExample(example);
	}

	public List<AdminBO> selectByType(String officeId, String userType) {
		return mapper.selectByType(officeId, userType);
	}

	public List<AdminBO> findByOffice(Long officeId) {
		return mapper.findByOffice(officeId);
	}

	public List<Admin> findAll() {
		AdminExample example = new AdminExample();

		List<Admin> list = new ArrayList<Admin>();
		List<AdminBO> boList = mapper.selectByExample(example);
		for (Admin user : boList) {
			list.add(user);
		}
		return list;
	}

	public Admin get(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public AdminBO getBOById(Long id) {
		return mapper.findDetailsById(id);
	}

	public void save(Admin entity,User currentUser) {
		if (exists(entity.getUid())) {
			entity.setCreateTime(new Date());
			entity.setCreateBy(String.valueOf(currentUser.getId()));
			entity.setUpdateTime(new Date());
			entity.setUpdateBy(String.valueOf(currentUser.getId()));
			update(entity);
		} else {
			entity.setCreateTime(new Date());
			entity.setCreateBy(String.valueOf(currentUser.getId()));
			insert(entity);
		}
	}

	public boolean exists(java.lang.Long id) {
		AdminExample example = new AdminExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}

	public void insert(Admin entity) {
		mapper.insertSelective(entity);
	}

	public void update(Admin entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	public void save(List<Admin> entityList,User currentUser) {
		for (Admin user : entityList) {
			save(user,currentUser);
		}
	}

	public int deleteById(Long uid) {
		Admin d = new Admin();
		d.setUid(uid);
		d.setIsDel(true);
		return mapper.updateByPrimaryKeySelective(d);
	}
	
	public void freezeUser(Long userId) 
	{
		AdminBO admin = getBOById(userId);
		Admin userUpdate = new Admin();
		userUpdate.setUid(admin.getUid());
		userUpdate.setStatus(admin.getStatus().equals(CommonConstants.USER_STATUS_NORMAL) ? CommonConstants.USER_STATUS_FREEZE : CommonConstants.USER_STATUS_NORMAL);
		mapper.updateByPrimaryKeySelective(userUpdate);
	}

	public void delete(Long id) {
		mapper.deleteByPrimaryKey(String.valueOf(id));
	}

}
