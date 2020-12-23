package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.MembershipCardBO;
import vc.thinker.cabbage.user.model.MembershipCard;
import vc.thinker.cabbage.user.model.MembershipCardExample;
import vc.thinker.cabbage.user.vo.MembershipCardVO;

public interface MembershipCardMapper {
    int countByExample(MembershipCardExample example);

    int deleteByExample(MembershipCardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MembershipCard record);

    int insertSelective(MembershipCard record);

    List<MembershipCardBO> selectByExample(MembershipCardExample example);

    MembershipCardBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MembershipCard record, @Param("example") MembershipCardExample example);

    int updateByExample(@Param("record") MembershipCard record, @Param("example") MembershipCardExample example);

    int updateByPrimaryKeySelective(MembershipCard record);

    int updateByPrimaryKey(MembershipCard record);

	List<MembershipCardBO> findPageByVo(@Param("page")Page<MembershipCardBO> page, @Param("vo")MembershipCardVO vo);
}