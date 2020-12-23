package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.model.VipPayLog;
import vc.thinker.cabbage.user.model.VipPayLogExample;
import vc.thinker.cabbage.user.vo.VipPayLogVO;
import vc.thinker.cabbage.common.MyPage;

public interface VipPayLogMapper {
    int countByExample(VipPayLogExample example);

    int deleteByExample(VipPayLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VipPayLog record);

    int insertSelective(VipPayLog record);

    List<VipPayLogBO> selectByExample(VipPayLogExample example);

    VipPayLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VipPayLog record, @Param("example") VipPayLogExample example);

    int updateByExample(@Param("record") VipPayLog record, @Param("example") VipPayLogExample example);

    int updateByPrimaryKeySelective(VipPayLog record);

    int updateByPrimaryKey(VipPayLog record);

	List<VipPayLogBO> findPageByVo(@Param("page")MyPage<VipPayLogBO> page, @Param("vo")VipPayLogVO vo);

	List<VipPayLogBO> findByStatusAndPayTime(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("status")Integer status);
}