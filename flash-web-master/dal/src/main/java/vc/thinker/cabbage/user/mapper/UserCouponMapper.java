package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import vc.thinker.cabbage.user.bo.UserCouponBO;
import vc.thinker.cabbage.user.model.UserCoupon;
import vc.thinker.cabbage.user.model.UserCouponExample;
import vc.thinker.cabbage.user.vo.UserCouponVO;
import vc.thinker.cabbage.common.MyPage;

public interface UserCouponMapper {
	int countByExample(UserCouponExample example);

	int deleteByExample(UserCouponExample example);

	int deleteByPrimaryKey(Long id);

	int insert(UserCoupon record);

	int insertSelective(UserCoupon record);

	List<UserCouponBO> selectByExample(UserCouponExample example);

	UserCouponBO selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

	int updateByExample(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

	int updateByPrimaryKeySelective(UserCoupon record);

	int updateByPrimaryKey(UserCoupon record);

	List<UserCouponBO> findCouponList(@Param("page") MyPage<UserCouponBO> page, @Param("vo") UserCouponVO vo);

	List<UserCouponBO> findCouponStatus();
}