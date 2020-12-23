package vc.thinker.cabbage.user.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.model.Member;
import vc.thinker.cabbage.user.model.MemberExample;
import vc.thinker.cabbage.user.vo.MemberVO;
import vc.thinker.cabbage.common.MyPage;

public interface MemberMapper {
	
	int incrDepositby(@Param("uid") Long uid,@Param("deposit") BigDecimal deposit);
	
	int incrRideDistanceby(@Param("uid") Long uid,@Param("rideDistance") Double rideDistance);

	int incrRideTimeby(@Param("uid") Long uid,@Param("rideTime") Long rideTime);
	
	List<MemberBO> findByPage(@Param("page")Page<MemberBO> page, @Param("vo")MemberVO vo);
	
	int updateUid(@Param("newUid") Long newUid,@Param("oldUid") Long oldUid);
	
	List<MemberBO> findMemberPageByVO(@Param("page")MyPage<MemberBO> page, @Param("vo")MemberVO vo);
	
    int countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(Member record);

    int insertSelective(Member record);

    List<MemberBO> selectByExample(MemberExample example);

    MemberBO selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);


	Integer countHadDepositeMembers();

	BigDecimal sumDeposit();
	
	BigDecimal sumDepositByCurrency(@Param("currency")String currency);

	List<CountStatsBO> findUserStats(@Param("vo")StatsVO vo);


	List<CountStatsBO> findDepositStats(@Param("vo")StatsVO vo);

	List<MemberBO> findHaveNicknameAndMobile(@Param("page")MyPage<MemberBO> page, @Param("vo")MemberVO vo);

	List<MemberBO> findId(Long id);

	List<MemberBO> findAllOnlyUid();

	List<MemberBO> findAllVipOrNotVip(@Param("isVip")boolean isVip);

	List<MemberBO> findByCreateTime(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

	List<MemberBO> findCheckListByPageAndVo(@Param("page")MyPage<MemberBO> page, @Param("vo")MemberVO vo);

	List<MemberBO> findByUids(@Param("uidList")List<Long> uidList);

	Integer countActiveMembers(@Param("day")String day);

	List<CountStatsBO> findActiveUserStats(@Param("vo")StatsVO vo);

	List<MemberBO> findListByMemberVO(@Param("page")MyPage<MemberBO> page,@Param("vo") MemberVO vo);
}