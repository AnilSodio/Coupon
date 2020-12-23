package vc.thinker.cabbage.user.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.DepositPayLogBO;
import vc.thinker.cabbage.user.model.DepositPayLog;
import vc.thinker.cabbage.user.model.DepositPayLogExample;
import vc.thinker.cabbage.user.vo.DepositPayLogVO;
import vc.thinker.cabbage.common.MyPage;

public interface DepositPayLogMapper {
    int countByExample(DepositPayLogExample example);

    int deleteByExample(DepositPayLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DepositPayLog record);

    int insertSelective(DepositPayLog record);

    List<DepositPayLogBO> selectByExample(DepositPayLogExample example);

    DepositPayLogBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DepositPayLog record, @Param("example") DepositPayLogExample example);

    int updateByExample(@Param("record") DepositPayLog record, @Param("example") DepositPayLogExample example);

    int updateByPrimaryKeySelective(DepositPayLog record);

    int updateByPrimaryKey(DepositPayLog record);

    DepositPayLogBO findLastOneByUidAndStutus(@Param("uid")Long uid,@Param("status")int status);

	List<DepositPayLogBO> findPageByVo(@Param("page")MyPage<DepositPayLogBO> page, @Param("vo")DepositPayLogVO vo);

	List<DepositPayLogBO> findLogListByVo(@Param("page")MyPage<DepositPayLogBO> page, @Param("vo")DepositPayLogVO vo);

	BigDecimal sumByData(@Param("date")String date);
}