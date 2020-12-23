package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.model.Admin;
import vc.thinker.cabbage.user.model.AdminExample;
import vc.thinker.cabbage.user.vo.AdminFindPageVO;
import vc.thinker.cabbage.common.MyPage;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(String id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<AdminBO> selectByExample(AdminExample example);
    
    List<AdminBO> selectByType(@Param("officeId") String officeId, @Param("userType") String userType);

    AdminBO selectByPrimaryKey(Long id);
    
    AdminBO findDetailsById(@Param("id") Long id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    List<AdminBO> findByRole(@Param("roleId") String roleId);
    
    List<AdminBO> findByOffice(Long officeId);
    
    List<AdminBO> findByPage(@Param("page") MyPage<AdminBO> page,@Param("vo") AdminFindPageVO vo);
}