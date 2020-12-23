package vc.thinker.cabbage.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.user.bo.UserBasicCostPayLogBO;
import vc.thinker.cabbage.user.model.UserBasicCostPayLog;
import vc.thinker.cabbage.user.model.UserBasicCostPayLogExample;
import vc.thinker.cabbage.user.vo.UserBasicCostPayLogVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserBasicCostPayLogMapper {
    int countByExample(UserBasicCostPayLogExample example);

    int deleteByExample(UserBasicCostPayLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBasicCostPayLog record);

    int insertSelective(UserBasicCostPayLog record);

    List<UserBasicCostPayLogBO> selectByExample(UserBasicCostPayLogExample example);

    UserBasicCostPayLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBasicCostPayLog record, @Param("example") UserBasicCostPayLogExample example);

    int updateByExample(@Param("record") UserBasicCostPayLog record, @Param("example") UserBasicCostPayLogExample example);

    int updateByPrimaryKeySelective(UserBasicCostPayLog record);

    int updateByPrimaryKey(UserBasicCostPayLog record);

	List<UserBasicCostPayLogBO> findPageByVo(@Param("page")MyPage<UserBasicCostPayLogBO> page, 
								@Param("vo")UserBasicCostPayLogVO vo);
}