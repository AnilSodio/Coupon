package vc.thinker.cabbage.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.dao.AdminDao;
import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.dao.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	/**
	 * 删除平台用户
	 * @param uid
	 */
	public void deletePlatformUser(Long uid){
		
		userDao.delete(uid);
		
		adminDao.delete(uid);
		
		UserAccountBO userAccount = userAccountDao.findByUid(uid,CommonConstants.ACCOUNT_TYPE_1);
		userAccountDao.delete(userAccount.getId());
	}
}
