package vc.thinker.cabbage.user.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.mapper.MemberMapper;
import vc.thinker.cabbage.user.model.Member;
import vc.thinker.cabbage.user.model.MemberExample;
import vc.thinker.cabbage.user.vo.MemberVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class MemberDao {

	@Autowired
	private MemberMapper mapper;

	/**
	 * 邀请码查找
	 * 
	 * @param inviteCode
	 * @return
	 */
	public MemberBO findByInviteCode(String inviteCode) {
		MemberExample example = new MemberExample();
		example.createCriteria().andInviteCodeEqualTo(inviteCode);

		List<MemberBO> list = mapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	public int incrDepositby(Long uid, BigDecimal deposit) {
		return mapper.incrDepositby(uid, deposit);
	}

	public int incrRideDistanceby(Long uid, Double rideDistance) {
		return mapper.incrRideDistanceby(uid, rideDistance);
	}

	public int incrRideTimeby(Long uid, Long rideTime) {
		return mapper.incrRideTimeby(uid, rideTime);
	}

	public List<MemberBO> findByPage(Page<MemberBO> page, MemberVO vo) {
		return mapper.findByPage(page, vo);
	}

	/**
	 * 修改uid
	 * 
	 * @param newUid
	 * @param oldUid
	 * @return
	 */
	public int updateUid(Long newUid, Long oldUid) {
		return mapper.updateUid(newUid, oldUid);
	}

	public List<MemberBO> findByIds(List<Long> ids) {
		MemberExample example = new MemberExample();
		example.createCriteria().andUidIn(ids);
		example.setOrderByClause("field(uid," + StringUtils.join(ids, ",") + ")");
		return mapper.selectByExample(example);
	}

	public List<MemberBO> findMemberPageByVO(MyPage<MemberBO> page, MemberVO vo) {
		return mapper.findMemberPageByVO(page, vo);
	}

	public MemberBO findMemberByMobile(String mobile) {
		MemberExample example = new MemberExample();
		example.createCriteria().andIsDeletedEqualTo(false).andMobileEqualTo(mobile)
				.andStatusEqualTo(CommonConstants.MEMBER_STATUS_ZC);
		List<MemberBO> list = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	public MemberBO findMemberByNickname(String nickName) {
		MemberExample example = new MemberExample();
		example.createCriteria().andIsDeletedEqualTo(false).andNicknameEqualTo(nickName)
				.andStatusEqualTo(CommonConstants.MEMBER_STATUS_ZC);
		List<MemberBO> list = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	/** generate code begin **/
	public List<MemberBO> findAll() {
		MemberExample example = new MemberExample();
		return mapper.selectByExample(example);
	}

	List<MemberBO> findAll(Iterable<Long> ids) {
		MemberExample example = new MemberExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}

	public long count() {
		MemberExample example = new MemberExample();
		return mapper.countByExample(example);
	}

	public List<Member> save(Iterable<Member> entities) {
		List<Member> list = new ArrayList<Member>();
		for (Member Member : entities) {
			list.add(save(Member));
		}
		return list;
	}

	public Member insert(Member record) {
		mapper.insertSelective(record);
		return record;
	}

	public Member save(Member record) {
		if (!exists(record.getUid())) {
			mapper.insertSelective(record);
		} else {
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}

	public void update(Member record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	public MemberBO findOne(java.lang.Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id) {
		MemberExample example = new MemberExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}

	public void delete(java.lang.Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void remove(java.lang.Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void delete(Member entity) {
		mapper.deleteByPrimaryKey(entity.getUid());
	}

	public void delete(Iterable<Member> entities) {
		List<Long> ids = Lists.newArrayList();
		for (Member entity : entities) {
			ids.add(entity.getUid());
		}
		deleteByIds(ids);
	}

	public void deleteByIds(Iterable<Long> ids) {
		MemberExample example = new MemberExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		mapper.deleteByExample(example);
	}

	public void deleteAll() {
		MemberExample example = new MemberExample();
		mapper.deleteByExample(example);
	}

	/** generate code end **/

	public Integer countMembers() {
		MemberExample example = new MemberExample();
		example.createCriteria().andIsDeletedEqualTo(false);
		return mapper.countByExample(example);
	}

	public Integer countHadDepositeMembers() {
		return mapper.countHadDepositeMembers();
	}

	public BigDecimal sumDeposit() {
		return mapper.sumDeposit();
	}

	public BigDecimal sumDepositByCurrency(String currency) {
		return mapper.sumDepositByCurrency(currency);
	}

	public List<CountStatsBO> findUserStats(StatsVO vo) {
		return mapper.findUserStats(vo);
	}

	public List<CountStatsBO> findDepositStats(StatsVO vo) {
		return mapper.findDepositStats(vo);
	}

	public List<MemberBO> findHaveNicknameAndMobile(MyPage<MemberBO> page, MemberVO vo) {
		return mapper.findHaveNicknameAndMobile(page, vo);
	}

	public List<MemberBO> findById(Long id) {
		return mapper.findId(id);
	}

	public List<MemberBO> selectByExample(MemberExample example) {
		return mapper.selectByExample(example);
	}

	public int countByExample(MemberExample example) {
		return mapper.countByExample(example);
	}

	public List<MemberBO> findAllOnlyUid() {
		return mapper.findAllOnlyUid();
	}

	public List<MemberBO> findAllVipOrNotVip(boolean isVip) {
		return mapper.findAllVipOrNotVip(isVip);
	}

	public List<MemberBO> findByCreateTime(String beginDate, String endDate) {
		return mapper.findByCreateTime(beginDate, endDate);
	}

	public List<MemberBO> findCheckListByPageAndVo(MyPage<MemberBO> page, MemberVO vo) {
		return mapper.findCheckListByPageAndVo(page, vo);
	}

	public List<MemberBO> findByUids(List<Long> uidList) {
		return mapper.findByUids(uidList);
	}

	public Integer countActiveMembers(String day) {
		return mapper.countActiveMembers(day);
	}

	public List<CountStatsBO> findActiveUserStats(StatsVO vo) {
		return mapper.findActiveUserStats(vo);
	}

	public List<MemberBO> findListByMemberVO(MyPage<MemberBO> page, MemberVO vo) {
		return mapper.findListByMemberVO(page, vo);
	}

}
