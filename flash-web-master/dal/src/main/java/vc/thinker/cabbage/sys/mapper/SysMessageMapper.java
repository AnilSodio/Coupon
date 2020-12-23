package vc.thinker.cabbage.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.sys.bo.SysMessageBO;
import vc.thinker.cabbage.sys.model.SysMessage;
import vc.thinker.cabbage.sys.model.SysMessageExample;
import vc.thinker.cabbage.sys.vo.SysMessageVO;

public interface SysMessageMapper {
	
	SysMessageBO findDetails(Long id);
	
	List<SysMessageBO> findPageByToUser(@Param("page")Page<SysMessageBO> page, @Param("vo")SysMessageVO vo);
	
    int countByExample(SysMessageExample example);

    int deleteByExample(SysMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    List<SysMessageBO> selectByExample(SysMessageExample example);

    SysMessageBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByExample(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
    
    List<SysMessageBO> findPageByVO(@Param("page")Page<SysMessageBO> page, @Param("vo")SysMessageVO vo);

	void insertByList(@Param("listMessage")List<SysMessageBO> listMessage);
}