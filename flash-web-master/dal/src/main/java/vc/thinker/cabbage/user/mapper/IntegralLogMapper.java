package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.IntegralLogBO;
import vc.thinker.cabbage.user.model.IntegralLog;
import vc.thinker.cabbage.user.model.IntegralLogExample;
import vc.thinker.cabbage.user.vo.IntegralLogVO;

public interface IntegralLogMapper {
    Long sumByExample(IntegralLogExample example);
    
    int countByExample(IntegralLogExample example);

    int deleteByExample(IntegralLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralLog record);

    int insertSelective(IntegralLog record);

    List<IntegralLogBO> selectByExample(IntegralLogExample example);

    IntegralLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralLog record, @Param("example") IntegralLogExample example);

    int updateByExample(@Param("record") IntegralLog record, @Param("example") IntegralLogExample example);

    int updateByPrimaryKeySelective(IntegralLog record);

    int updateByPrimaryKey(IntegralLog record);

	List<IntegralLogBO> findPageByVo(@Param("page")Page<IntegralLogBO> page, @Param("vo")IntegralLogVO vo);
}
