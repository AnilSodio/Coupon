package vc.thinker.cabbage.se.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.model.CabinetType;
import vc.thinker.cabbage.se.model.CabinetTypeExample;
import vc.thinker.cabbage.se.vo.CabinetTypeVO;
import vc.thinker.cabbage.common.MyPage;

public interface CabinetTypeMapper {
    int countByExample(CabinetTypeExample example);

    int deleteByExample(CabinetTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CabinetType record);

    int insertSelective(CabinetType record);

    List<CabinetTypeBO> selectByExample(CabinetTypeExample example);

    CabinetTypeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CabinetType record, @Param("example") CabinetTypeExample example);

    int updateByExample(@Param("record") CabinetType record, @Param("example") CabinetTypeExample example);

    int updateByPrimaryKeySelective(CabinetType record);

    int updateByPrimaryKey(CabinetType record);

	List<CabinetTypeBO> findPageByVo(@Param("page")MyPage<CabinetTypeBO> page, @Param("vo")CabinetTypeVO vo);
}