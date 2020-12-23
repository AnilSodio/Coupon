package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.SysCodeBO;
import vc.thinker.cabbage.se.model.SysCode;
import vc.thinker.cabbage.se.model.SysCodeExample;

public interface SysCodeMapper {
    int countByExample(SysCodeExample example);

    int deleteByExample(SysCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    List<SysCodeBO> selectByExample(SysCodeExample example);

    SysCodeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysCode record, @Param("example") SysCodeExample example);

    int updateByExample(@Param("record") SysCode record, @Param("example") SysCodeExample example);

    int updateByPrimaryKeySelective(SysCode record);

    int updateByPrimaryKey(SysCode record);
}