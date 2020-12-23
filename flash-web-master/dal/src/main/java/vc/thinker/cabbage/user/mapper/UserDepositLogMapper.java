package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.model.UserDepositLog;
import vc.thinker.cabbage.user.model.UserDepositLogExample;
import vc.thinker.cabbage.user.vo.UserDepositLogVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserDepositLogMapper {
    int countByExample(UserDepositLogExample example);

    int deleteByExample(UserDepositLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDepositLog record);

    int insertSelective(UserDepositLog record);

    List<UserDepositLogBO> selectByExample(UserDepositLogExample example);

    UserDepositLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDepositLog record, @Param("example") UserDepositLogExample example);

    int updateByExample(@Param("record") UserDepositLog record, @Param("example") UserDepositLogExample example);

    int updateByPrimaryKeySelective(UserDepositLog record);

    int updateByPrimaryKey(UserDepositLog record);

	List<UserDepositLogBO> findPageByVO(@Param("page")Page<UserDepositLogBO> page, @Param("vo")UserDepositLogVO vo);
	
	List<UserDepositLogBO> selectPageByUid(@Param("page")MyPage<UserDepositLogBO> page, @Param("vo")UserDepositLogVO vo);

	List<CountStatsBO> findDepositStats(@Param("vo")StatsVO vo);

}