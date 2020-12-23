package vc.thinker.cabbage.user.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.UserMoneyRechargeBO;
import vc.thinker.cabbage.user.model.UserMoneyRecharge;
import vc.thinker.cabbage.user.model.UserMoneyRechargeExample;
import vc.thinker.cabbage.user.vo.UserMoneyRechargeVO;

public interface UserMoneyRechargeMapper {
    int countByExample(UserMoneyRechargeExample example);

    int deleteByExample(UserMoneyRechargeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMoneyRecharge record);

    int insertSelective(UserMoneyRecharge record);

    List<UserMoneyRechargeBO> selectByExample(UserMoneyRechargeExample example);

    UserMoneyRechargeBO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMoneyRecharge record, @Param("example") UserMoneyRechargeExample example);

    int updateByExample(@Param("record") UserMoneyRecharge record, @Param("example") UserMoneyRechargeExample example);

    int updateByPrimaryKeySelective(UserMoneyRecharge record);

    int updateByPrimaryKey(UserMoneyRecharge record);

	List<UserMoneyRechargeBO> findPageByVo(@Param("page")Page<UserMoneyRechargeBO> page, @Param("vo")UserMoneyRechargeVO vo);

    int chargedUserNum();

    Double totalCharged();

	BigDecimal sumByDate(@Param("date")String date);

}