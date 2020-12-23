package vc.thinker.cabbage.user.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.model.UserMoneyExample;

public interface UserMoneyMapper {
	
	BigDecimal selectSumBalance();
	
	int subtractMoney(@Param("uid")Long uid,@Param("money") BigDecimal money);
	
	int addMoney(@Param("uid")Long uid,@Param("money") BigDecimal money);
	
    int countByExample(UserMoneyExample example);

    int deleteByExample(UserMoneyExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(UserMoney record);

    int insertSelective(UserMoney record);

    List<UserMoneyBO> selectByExample(UserMoneyExample example);

    UserMoneyBO selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") UserMoney record, @Param("example") UserMoneyExample example);

    int updateByExample(@Param("record") UserMoney record, @Param("example") UserMoneyExample example);

    int updateByPrimaryKeySelective(UserMoney record);

    int updateByPrimaryKey(UserMoney record);

	int addRebateMoney(@Param("uid")Long id,@Param("money") BigDecimal money);

	int substractRebateMoney(@Param("uid")Long uid, @Param("money")BigDecimal money);

	List<UserMoneyBO> sumRebateMoney();
}