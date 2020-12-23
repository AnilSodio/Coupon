package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.FeedbackMessageBO;
import vc.thinker.cabbage.se.model.FeedbackMessage;
import vc.thinker.cabbage.se.model.FeedbackMessageExample;
import vc.thinker.cabbage.se.vo.FeedbackMessageVO;
import vc.thinker.cabbage.common.MyPage;

public interface FeedbackMessageMapper {
    int countByExample(FeedbackMessageExample example);

    int deleteByExample(FeedbackMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FeedbackMessage record);

    int insertSelective(FeedbackMessage record);

    List<FeedbackMessageBO> selectByExample(FeedbackMessageExample example);

    FeedbackMessageBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FeedbackMessage record, @Param("example") FeedbackMessageExample example);

    int updateByExample(@Param("record") FeedbackMessage record, @Param("example") FeedbackMessageExample example);

    int updateByPrimaryKeySelective(FeedbackMessage record);

    int updateByPrimaryKey(FeedbackMessage record);

	List<FeedbackMessageBO> findPageByVo(@Param("page")MyPage<FeedbackMessageBO> page, @Param("vo")FeedbackMessageVO vo);

	void updateIsReaded(@Param("page")MyPage<FeedbackMessageBO> page);
}