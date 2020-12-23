package vc.thinker.cabbage.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import vc.thinker.cabbage.x.XDao;
import vc.thinker.cabbage.common.MyPage;

import java.util.List;

/**
 * HTH
 * @param <BO>
 * @param <VO>
 * @param <Dao>
 */
public class XService<BO, VO, Dao extends XDao> {
    @Autowired
    protected Dao dao;

    public List<BO> findListByPageAndVO(MyPage<BO> page, VO vo) {
        return dao.findListByPageAndVO(page, vo);
    }

    public BO findOne(Long id) {
        return (BO)dao.findOne(id);
    }

    public BO save(BO bo) {
        return (BO) dao.save(bo);
    }

    public void delete(Long id) {
        dao.delete(id);
    }
}
