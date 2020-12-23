package vc.thinker.cabbage.se.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.model.Feedback;
import vc.thinker.cabbage.se.model.FeedbackExample;
import vc.thinker.cabbage.se.vo.FeedbackVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

public interface FeedbackMapper {
    int countByExample(FeedbackExample example);

    int deleteByExample(FeedbackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    List<FeedbackBO> selectByExample(FeedbackExample example);

    FeedbackBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

	List<FeedbackTypeBO> findPageByVo(@Param("page")MyPage<FeedbackBO> page, @Param("vo")FeedbackVO vo);

	FeedbackBO findDetailedOne(Long id);

	List<FeedbackBO> countByTypeId();

	List<FeedbackBO> groupByFeedType();

	List<CountStatsBO> feedbackStats(@Param("vo")StatsVO vo);

	BigDecimal countByTime(@Param("year")String year, @Param("month")String month,@Param("isUsing")Boolean isUsing);
}