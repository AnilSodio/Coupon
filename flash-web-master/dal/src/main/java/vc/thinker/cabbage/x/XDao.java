package vc.thinker.cabbage.x;

import org.springframework.beans.factory.annotation.Autowired;
import vc.thinker.cabbage.common.MyPage;

import java.util.List;

/**
 * HTH
 * @param <BO>
 * @param <VO>
 * @param <Mapper>
 * @param <Model>
 */
public abstract class XDao<BO, VO, Mapper extends XMapper,Model> {
    @Autowired
    protected Mapper mapper;

    public List<BO> findListByPageAndVO(MyPage<BO> page, VO vo) {
        return mapper.findListByPageAndVO(page, vo);
    }

    public BO findOne(Long id) {
        return (BO) mapper.selectByPrimaryKey(id);
    }

    public abstract Model save(Model bo);//留给子类重写

    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
