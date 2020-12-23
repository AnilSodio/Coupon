package vc.thinker.cabbage.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.user.bo.UniqueRadomCodeBO;
import vc.thinker.cabbage.user.dao.UniqueRadomCodeDao;

@Service
@Transactional
public class UniqueRadomCodeService {
	
	@Autowired
	private UniqueRadomCodeDao codeDao;
	/**
	 * 根据类型获取随机码
	 * @param type
	 * @return
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public String getCode(String type){
		//根据type获取，没有获取到插入1万条
		UniqueRadomCodeBO code = codeDao.getCode(type);
		if(code==null){
			codeDao.addNewCode(type,10000);
			code = codeDao.getCode(type);
		}
		return code.getCode().toString();
	};
	
	
	/**
	 * 批量获取随机码
	 * @param type类型
	 * @param num数量
	 * @return
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public List<String> getListCode(String type,int num){
		//根据type获取，没有获取到插入1万条
		List<String> code = codeDao.getListCode(type,num);
		if(code==null||code.size()<num){
			codeDao.addNewCode(type,10000);
			code = codeDao.getListCode(type,num);
		}
		return code;
	};
}
