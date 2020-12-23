package vc.thinker.cabbage.sys.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vc.thinker.cabbage.sys.bo.ResourceBO;
import vc.thinker.cabbage.sys.dao.ResourceDao;
import vc.thinker.cabbage.sys.vo.ResourceVO;

@Service
@Transactional
public class ResourceService extends XService<ResourceBO, ResourceVO, ResourceDao> {
}
