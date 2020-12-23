package vc.thinker.cabbage.money.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.money.util.DateTimeUtils;
import vc.thinker.cabbage.user.bo.UserMoneyRechargeBO;
import vc.thinker.cabbage.user.dao.UserMoneyRechargeDao;
import vc.thinker.cabbage.user.vo.UserMoneyRechargeVO;

/**
 * 用户余额充值相关操作业务层
 * @author thinker
 *
 */
@Service
@Transactional
public class UserMoneyRechargeService {
	
	private Logger logger = LoggerFactory.getLogger(UserMoneyRechargeService.class);

	@Autowired
	private UserMoneyRechargeDao userMoneyRechargeDao;

	/**
	 * 分页查询
	 * @param page 分页参数
	 * @param vo 查询参数
	 * @return
	 */
	public List<UserMoneyRechargeBO> findPageByVo(Page<UserMoneyRechargeBO> page, UserMoneyRechargeVO vo) {
		try{
			if(StringUtils.isNotBlank(vo.getStartTime())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				vo.setGtTime(sdf.parse(vo.getStartTime()));
			}
		 
			if(StringUtils.isNotBlank(vo.getEndTime())){
				vo.setLtTime(DateTimeUtils.getAppoint59SecondDate(vo.getEndTime()));
			}
		}catch (Exception e) {
			logger.info("用户充值记录查询日期格式转换出错！");
		}
		return userMoneyRechargeDao.findPageByVo(page,vo);
	}

	public UserMoneyRechargeBO findOne(Long id) {
		return userMoneyRechargeDao.findOne(id);
	}
	
	
}
