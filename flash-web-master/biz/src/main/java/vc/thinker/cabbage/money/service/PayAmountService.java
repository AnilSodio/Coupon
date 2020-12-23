package vc.thinker.cabbage.money.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.area.Country;
import com.sinco.common.area.CountryUtil;

import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.PayAmountBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.PayAmountDao;
import vc.thinker.cabbage.user.model.PayAmountExample;
import vc.thinker.cabbage.user.vo.PayAmountVO;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.sys.model.SysSetting;
import vc.thinker.sys.model.User;

@Service
@Transactional(rollbackFor = ServiceException.class)
public class PayAmountService {

	@Autowired
	private PayAmountDao payAmountDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	/**
	 * 查找正常的充值金额
	 * @return
	 */
	public List<PayAmountBO> findNormal(Long uid) {
		String currency = sysSettingDao.findOne().getPlayformDefaultCurrency();
//		MemberBO member=memberDao.findOne(uid);
//		Country country=CountryUtil.get(member.getCountry());
		return payAmountDao.findNormal(currency);
	}
	
	 /* 分页查询
	 * @param page 分页参数
	 * @param vo 查询参数
	 * @return
	 */
	public List<PayAmountBO> findPageByVo(MyPage<PayAmountBO> page, PayAmountVO vo) {
		return payAmountDao.findPageByVo(page,vo);
	}

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public PayAmountBO findOne(Long id) {
		return payAmountDao.findOne(id);
	}

	/**
	 * 修改或者删除操作
	 * @param user 操作人
	 * @param bo 
	 */
	public void saveOrUpdate(User user, PayAmountBO bo) {
		if(null != bo.getId()){
			//修改
			bo.setUpdateBy(user.getId());
			bo.setUpdateTime(new Date());
		}else {
			bo.setCreateBy(user.getId());
			bo.setCreateTime(new Date());
		}
		
		SysSetting findOne = sysSettingDao.findOne();
		bo.setCurrency(findOne.getPlayformDefaultCurrency());
		payAmountDao.save(bo);
	}

	/**
	 * 主键删除
	 * @param id
	 */
	public void delete(Long id) {
		payAmountDao.delete(id);
	}

	/**
	 * 金额校验（平台后台用）
	 * @param id
	 * @param payAmount
	 * @return
	 */
	public Boolean checkPayAmount(Long id, BigDecimal payAmount) {
		if(null != id){
			//修改
			PayAmountBO info = payAmountDao.findOne(id);
			if(info.getPayAmount().compareTo(payAmount) != 0){
				Boolean result = isExitPayamount(payAmount);
				if(result){
					return false;
				}
			}
		}else {
			//新增
			Boolean result = isExitPayamount(payAmount);
			if(result){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 校验金额是否已经存在
	 * @param payAmount
	 * @return 
	 */
	public Boolean isExitPayamount(BigDecimal payAmount){
		
		if(null == payAmount){
			return false;
		}
		
		PayAmountExample example = new PayAmountExample();
		example.createCriteria().andPayAmountEqualTo(payAmount);
		List<PayAmountBO> re_list = payAmountDao.selectByExample(example);
		
		if(null != re_list && re_list.size()>0){
			return true;
		}
		
		return false;
	}
}
