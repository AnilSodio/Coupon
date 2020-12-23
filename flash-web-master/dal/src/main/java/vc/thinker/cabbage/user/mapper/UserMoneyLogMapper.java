package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.model.UserMoneyLog;
import vc.thinker.cabbage.user.model.UserMoneyLogExample;
import vc.thinker.cabbage.user.vo.UserMoneyLogVO;

public interface UserMoneyLogMapper {
	
	List<UserMoneyLogBO> findPageByVO(@Param("vo")UserMoneyLogVO vo, Page<UserMoneyLogBO> page);
	
    int countByExample(UserMoneyLogExample example);

    int deleteByExample(UserMoneyLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMoneyLog record);

    int insertSelective(UserMoneyLog record);

    List<UserMoneyLogBO> selectByExampleWithBLOBs(UserMoneyLogExample example);

    List<UserMoneyLogBO> selectByExample(UserMoneyLogExample example);

    UserMoneyLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMoneyLog record, @Param("example") UserMoneyLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UserMoneyLog record, @Param("example") UserMoneyLogExample example);

    int updateByExample(@Param("record") UserMoneyLog record, @Param("example") UserMoneyLogExample example);

    int updateByPrimaryKeySelective(UserMoneyLog record);

    int updateByPrimaryKeyWithBLOBs(UserMoneyLog record);

    int updateByPrimaryKey(UserMoneyLog record);

	List<UserMoneyLogBO> listPageByVo(@Param("page")Page<UserMoneyLogBO> page, @Param("vo")UserMoneyLogVO vo);
}