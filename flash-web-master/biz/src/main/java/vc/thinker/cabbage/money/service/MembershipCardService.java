package vc.thinker.cabbage.money.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.sys.dao.SysSettingDao;
import vc.thinker.cabbage.user.bo.MembershipCardBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.user.dao.MembershipCardDao;
import vc.thinker.cabbage.user.model.MembershipCardExample;
import vc.thinker.cabbage.user.vo.MembershipCardVO;

@Service
@Transactional
public class MembershipCardService {

	@Autowired
	private MembershipCardDao membershipCardDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SysSettingDao sysSettingDao;
	
	
	/**
	 * 查找会员卡
	 * @return
	 */
	public List<MembershipCardBO> findMembershipCardList(Long uid){
//		MemberBO member=memberDao.findOne(uid);
//		Country country=CountryUtil.get(member.getCountry());
		String currency = sysSettingDao.findOne().getPlayformDefaultCurrency();
		return membershipCardDao.findNormal(currency);
	}

	/**
	 * 分页查询
	 * @param page 分页参数
	 * @param vo 查询参数
	 * @return
	 */
	public List<MembershipCardBO> findPageByVo(Page<MembershipCardBO> page, MembershipCardVO vo) {
		return membershipCardDao.findPageByVo(page,vo);
	}

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public MembershipCardBO findOne(Long id) {
		return membershipCardDao.findOne(id);
	}

	/**
	 * 新增或修改
	 * @param user 当前操作人
	 * @param bo
	 */
	public void saveOrUpdate(Long userId, MembershipCardBO bo) {
		
		if(null != bo.getId()){
			bo.setUpdateBy(userId);
			bo.setUpdateTime(new Date());
		}else {
			bo.setCreateBy(userId);
			bo.setCreateTime(new Date());
			bo.setDiscount(new BigDecimal(0));
		}
		
		String currency = sysSettingDao.findOne().getPlayformDefaultCurrency();
		bo.setCurrency(currency);
		membershipCardDao.save(bo);
	}

	/**
	 * 删除操作
	 * @param id
	 */
	public void delete(Long id) {
		membershipCardDao.delete(id);
	}

	/**
	 * 校验会员卡名称
	 * @param id
	 * @param cardName
	 * @return
	 */
	public Boolean checkCardName(Long id, String cardName) {
		
		if(null != id){
			MembershipCardBO info = membershipCardDao.findOne(id);
			if(!cardName.equals(info.getCardName())){
				Boolean result = cardIsExit(cardName);
				if(result){
					return false;
				}
			}
		}else {
			Boolean result = cardIsExit(cardName);
			if(result){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 查询会员卡是否以及存在
	 * @param cardName
	 * @return
	 */
	public Boolean cardIsExit(String cardName){
		
		MembershipCardExample example = new MembershipCardExample();
		example.createCriteria().andCardNameEqualTo(cardName);
		
		List<MembershipCardBO> rs_list = membershipCardDao.selectByExample(example);
		
		if(null != rs_list && rs_list.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 查询所有的会员卡
	 * @return
	 */
	public List<MembershipCardBO> findAll() {
		return membershipCardDao.findAll();
	}
}
