package vc.thinker.cabbage.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.bo.UserGuideBO;
import vc.thinker.cabbage.sys.dao.UserGuideDao;
import vc.thinker.cabbage.sys.vo.UserGuideVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class UserGuideService {

	@Autowired
	private UserGuideDao userGuideDao;

	public List<UserGuideBO> finePageByVo(MyPage<UserGuideBO> page, UserGuideVO vo) {
		return userGuideDao.finePageByVo(page,vo);
	}

	public UserGuideBO findOne(Long id) {
		return userGuideDao.findOne(id);
	}

	public void saveOrUpdate(UserGuideBO bo) {
		if(null != bo.getId()){
		}else {
			bo.setCreateTime(new Date());
		}
		userGuideDao.save(bo);
	}

	public void delete(Long id) {
		userGuideDao.delete(id);
	}

	public List<UserGuideBO> findListByType(Integer type,String language) {
		return userGuideDao.findListByType(type,language);
	}
}
