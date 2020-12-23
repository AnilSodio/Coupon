package vc.thinker.cabbage.x;

import org.apache.ibatis.annotations.Param;
import vc.thinker.cabbage.common.MyPage;

import java.util.List;

/**
 * HTH
 * @param <BO>
 * @param <VO>
 */
public interface XMapper<BO, VO> {
    List<BO> findListByPageAndVO(@Param("page") MyPage<BO> page, @Param("vo") VO vo);

//    int insertSelective(BO record);
//
//    int updateByPrimaryKeySelective(BO record);

    BO selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);
}
