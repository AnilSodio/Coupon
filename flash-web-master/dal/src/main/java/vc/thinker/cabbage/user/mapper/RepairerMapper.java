package vc.thinker.cabbage.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.user.bo.RepairerBO;
import vc.thinker.cabbage.user.model.Repairer;
import vc.thinker.cabbage.user.model.RepairerExample;
import vc.thinker.cabbage.user.vo.RepairerVO;
import vc.thinker.cabbage.common.MyPage;

public interface RepairerMapper {
    int countByExample(RepairerExample example);

    int deleteByExample(RepairerExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(Repairer record);

    int insertSelective(Repairer record);

    List<RepairerBO> selectByExample(RepairerExample example);

    RepairerBO selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Repairer record, @Param("example") RepairerExample example);

    int updateByExample(@Param("record") Repairer record, @Param("example") RepairerExample example);

    int updateByPrimaryKeySelective(Repairer record);

    int updateByPrimaryKey(Repairer record);

    List<RepairerBO> findPageByVo(@Param("page")MyPage<RepairerBO> page, @Param("vo")RepairerVO vo);

	RepairerBO findDetailInfo(Long uid);

	List<RepairerBO> findByUids(@Param("uidList")List<Long> uidList);
}