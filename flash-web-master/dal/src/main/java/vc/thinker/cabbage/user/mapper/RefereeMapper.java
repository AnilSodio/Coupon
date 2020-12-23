package vc.thinker.cabbage.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.model.Referee;
import vc.thinker.cabbage.user.model.RefereeExample;
import vc.thinker.cabbage.user.vo.RefereeVO;
import vc.thinker.cabbage.common.MyPage;

public interface RefereeMapper {
    int countByExample(RefereeExample example);

    int deleteByExample(RefereeExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(Referee record);

    int insertSelective(Referee record);

    List<RefereeBO> selectByExample(RefereeExample example);

    RefereeBO selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Referee record, @Param("example") RefereeExample example);

    int updateByExample(@Param("record") Referee record, @Param("example") RefereeExample example);

    int updateByPrimaryKeySelective(Referee record);

    int updateByPrimaryKey(Referee record);

	List<RefereeBO> findPageByVo(@Param("page")MyPage<RefereeBO> page, @Param("vo")RefereeVO vo);
}