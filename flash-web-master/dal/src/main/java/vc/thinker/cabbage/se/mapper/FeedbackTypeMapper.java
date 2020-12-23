package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.model.FeedbackType;
import vc.thinker.cabbage.se.model.FeedbackTypeExample;
import vc.thinker.cabbage.se.vo.FeedbackTypeVO;
import vc.thinker.cabbage.common.MyPage;

public interface FeedbackTypeMapper {
    int countByExample(FeedbackTypeExample example);

    int deleteByExample(FeedbackTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FeedbackType record);

    int insertSelective(FeedbackType record);

    List<FeedbackTypeBO> selectByExample(FeedbackTypeExample example);

    FeedbackTypeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FeedbackType record, @Param("example") FeedbackTypeExample example);

    int updateByExample(@Param("record") FeedbackType record, @Param("example") FeedbackTypeExample example);

    int updateByPrimaryKeySelective(FeedbackType record);

    int updateByPrimaryKey(FeedbackType record);

	List<FeedbackTypeBO> findPageByVo(@Param("page")MyPage<FeedbackTypeBO> page, @Param("vo")FeedbackTypeVO vo);
}