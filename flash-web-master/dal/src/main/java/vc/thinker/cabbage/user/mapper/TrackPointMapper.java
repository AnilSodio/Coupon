package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.TrackPointBO;
import vc.thinker.cabbage.user.model.TrackPoint;
import vc.thinker.cabbage.user.model.TrackPointExample;

public interface TrackPointMapper {
    int countByExample(TrackPointExample example);

    int deleteByExample(TrackPointExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrackPoint record);

    int insertSelective(TrackPoint record);

    List<TrackPointBO> selectByExample(TrackPointExample example);

    TrackPointBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrackPoint record, @Param("example") TrackPointExample example);

    int updateByExample(@Param("record") TrackPoint record, @Param("example") TrackPointExample example);

    int updateByPrimaryKeySelective(TrackPoint record);

    int updateByPrimaryKey(TrackPoint record);
}