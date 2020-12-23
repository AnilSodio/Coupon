package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.bo.CabinetFirmwareBO;
import vc.thinker.cabbage.se.model.CabinetFirmware;
import vc.thinker.cabbage.se.model.CabinetFirmwareExample;
import vc.thinker.cabbage.se.vo.CabinetFirmwareVO;

public interface CabinetFirmwareMapper {
    int countByExample(CabinetFirmwareExample example);

    int deleteByExample(CabinetFirmwareExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CabinetFirmware record);

    int insertSelective(CabinetFirmware record);

    List<CabinetFirmwareBO> selectByExample(CabinetFirmwareExample example);

    CabinetFirmwareBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CabinetFirmware record, @Param("example") CabinetFirmwareExample example);

    int updateByExample(@Param("record") CabinetFirmware record, @Param("example") CabinetFirmwareExample example);

    int updateByPrimaryKeySelective(CabinetFirmware record);

    int updateByPrimaryKey(CabinetFirmware record);

	List<CabinetFirmwareBO> findPageByVo(@Param("page") Page<CabinetFirmwareBO> page, 
			@Param("vo")CabinetFirmwareVO vo);

}