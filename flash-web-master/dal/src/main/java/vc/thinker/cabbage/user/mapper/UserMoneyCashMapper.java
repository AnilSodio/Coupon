package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.model.UserMoneyCash;
import vc.thinker.cabbage.user.model.UserMoneyCashExample;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserMoneyCashMapper {
    int countByExample(UserMoneyCashExample example);

    int deleteByExample(UserMoneyCashExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMoneyCash record);

    int insertSelective(UserMoneyCash record);

    List<UserMoneyCashBO> findPageByVO(@Param("vo") UserMoneyCashVO vo, Page<UserMoneyCashBO> page);
    
    List<UserMoneyCashBO> selectByExample(UserMoneyCashExample example);

    UserMoneyCashBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMoneyCash record, @Param("example") UserMoneyCashExample example);

    int updateByExample(@Param("record") UserMoneyCash record, @Param("example") UserMoneyCashExample example);

    int updateByPrimaryKeySelective(UserMoneyCash record);

    int updateByPrimaryKey(UserMoneyCash record);

	UserMoneyCashBO findDetailOne(Long id);

	List<UserMoneyCashBO> findPageByReferee(@Param("page")MyPage<UserMoneyCashBO> page, @Param("vo")UserMoneyCashVO vo);

	List<UserMoneyCashBO> findPageBySeller(@Param("page")MyPage<UserMoneyCashBO> page, @Param("vo")UserMoneyCashVO vo);

	List<UserMoneyCashBO> findPageByAgent(@Param("page")MyPage<UserMoneyCashBO> page, @Param("vo")UserMoneyCashVO vo);
}