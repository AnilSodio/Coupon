package vc.thinker.cabbage.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.geo.Point;

import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.model.Seller;
import vc.thinker.cabbage.user.model.SellerExample;
import vc.thinker.cabbage.user.vo.SellerVO;
import vc.thinker.cabbage.common.MyPage;

public interface SellerMapper {
	
	List<SellerBO> findNormalByLocation(@Param("p") Point p,@Param("distance") Integer distance);
	
    int countByExample(SellerExample example);

    int deleteByExample(SellerExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(Seller record);

    int insertSelective(Seller record);

    List<SellerBO> selectByExample(SellerExample example);

    SellerBO selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Seller record, @Param("example") SellerExample example);

    int updateByExample(@Param("record") Seller record, @Param("example") SellerExample example);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

	List<SellerBO> findPageByVo(@Param("page")MyPage<SellerBO> page, @Param("vo")SellerVO vo);

	SellerBO findOnlyUidAndNameByUid(Long uid);
}