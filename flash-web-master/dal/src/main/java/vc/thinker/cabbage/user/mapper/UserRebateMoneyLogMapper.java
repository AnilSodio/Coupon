package vc.thinker.cabbage.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.user.bo.UserRebateMoneyLogBO;
import vc.thinker.cabbage.user.model.UserRebateMoneyLog;
import vc.thinker.cabbage.user.model.UserRebateMoneyLogExample;
import vc.thinker.cabbage.user.vo.UserRebateMoneyLogVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserRebateMoneyLogMapper {
    int countByExample(UserRebateMoneyLogExample example);

    int deleteByExample(UserRebateMoneyLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRebateMoneyLog record);

    int insertSelective(UserRebateMoneyLog record);

    List<UserRebateMoneyLogBO> selectByExample(UserRebateMoneyLogExample example);

    UserRebateMoneyLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRebateMoneyLog record, @Param("example") UserRebateMoneyLogExample example);

    int updateByExample(@Param("record") UserRebateMoneyLog record, @Param("example") UserRebateMoneyLogExample example);

    int updateByPrimaryKeySelective(UserRebateMoneyLog record);

    int updateByPrimaryKey(UserRebateMoneyLog record);

	List<UserRebateMoneyLogBO> findPageByVo(@Param("page")MyPage<UserRebateMoneyLogBO> page, @Param("vo")UserRebateMoneyLogVO vo);
}