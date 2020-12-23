package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.sys.bo.InitImgBO;
import vc.thinker.cabbage.sys.model.InitImg;
import vc.thinker.cabbage.sys.model.InitImgExample;
import vc.thinker.cabbage.sys.vo.InitImgVO;
import vc.thinker.cabbage.common.MyPage;

public interface InitImgMapper {
    int countByExample(InitImgExample example);

    int deleteByExample(InitImgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InitImg record);

    int insertSelective(InitImg record);

    List<InitImgBO> selectByExample(InitImgExample example);

    InitImgBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InitImg record, @Param("example") InitImgExample example);

    int updateByExample(@Param("record") InitImg record, @Param("example") InitImgExample example);

    int updateByPrimaryKeySelective(InitImg record);

    int updateByPrimaryKey(InitImg record);

	List<InitImgBO> findPageByVo(@Param("page")MyPage<InitImgBO> page, @Param("vo")InitImgVO vo);

	List<InitImgBO> findNormalImg(@Param("imgType")Integer imgType);
}