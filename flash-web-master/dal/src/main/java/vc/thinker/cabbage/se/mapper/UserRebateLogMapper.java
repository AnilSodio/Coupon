package vc.thinker.cabbage.se.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.model.UserRebateLog;
import vc.thinker.cabbage.se.model.UserRebateLogExample;
import vc.thinker.cabbage.se.vo.UserRebateLogVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.CountStatsVO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserRebateLogMapper {
    int countByExample(UserRebateLogExample example);

    int deleteByExample(UserRebateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRebateLog record);

    int insertSelective(UserRebateLog record);

    List<UserRebateLogBO> selectByExample(UserRebateLogExample example);

    UserRebateLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRebateLog record, @Param("example") UserRebateLogExample example);

    int updateByExample(@Param("record") UserRebateLog record, @Param("example") UserRebateLogExample example);

    int updateByPrimaryKeySelective(UserRebateLog record);

    int updateByPrimaryKey(UserRebateLog record);

	List<UserRebateLogBO> findPageByVo(@Param("page") MyPage<UserRebateLogBO> page, @Param("vo") UserRebateLogVO vo);

	List<CountStatsBO> findRebateStats(@Param("vo")StatsVO vo);

	BigDecimal sumRebate(@Param("uid")Long uid,@Param("date") String date);

	List<UserRebateLogBO> sellerIncomeStats(@Param("vo")StatsVO vo);
	
	List<UserRebateLogBO> refereeIncomeStats(@Param("vo")StatsVO vo);
	
	List<UserRebateLogBO> agentIncomeStats(@Param("vo")StatsVO vo);

	List<UserRebateLogBO> findPageByAgent(@Param("page") MyPage<UserRebateLogBO> page, @Param("vo") UserRebateLogVO vo);

	List<UserRebateLogBO> findPageByReferee(@Param("page") MyPage<UserRebateLogBO> page, @Param("vo") UserRebateLogVO vo);

	List<UserRebateLogBO> findPageBySeller(@Param("page")MyPage<UserRebateLogBO> page, @Param("vo")UserRebateLogVO vo);

	List<UserRebateLogBO> findPageByAdmin(@Param("page")MyPage<UserRebateLogBO> page, @Param("vo")UserRebateLogVO vo);

	List<UserRebateLogBO> sumByCurrency();
}