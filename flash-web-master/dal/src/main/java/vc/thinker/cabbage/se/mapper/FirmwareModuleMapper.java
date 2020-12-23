package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.FirmwareModuleBO;
import vc.thinker.cabbage.se.model.FirmwareModule;
import vc.thinker.cabbage.se.model.FirmwareModuleExample;

public interface FirmwareModuleMapper {
    int countByExample(FirmwareModuleExample example);

    int deleteByExample(FirmwareModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FirmwareModule record);

    int insertSelective(FirmwareModule record);

    List<FirmwareModuleBO> selectByExample(FirmwareModuleExample example);

    FirmwareModuleBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FirmwareModule record, @Param("example") FirmwareModuleExample example);

    int updateByExample(@Param("record") FirmwareModule record, @Param("example") FirmwareModuleExample example);

    int updateByPrimaryKeySelective(FirmwareModule record);

    int updateByPrimaryKey(FirmwareModule record);
}