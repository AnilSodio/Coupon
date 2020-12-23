package vc.thinker.cabbage.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.bo.ShareSetBO;
import vc.thinker.cabbage.sys.dao.ShareSetDao;
import vc.thinker.cabbage.sys.vo.ShareSetVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class ShareService {

	@Autowired
	private ShareSetDao shareSetDao;

	public List<ShareSetBO> findPageByVo(MyPage<ShareSetBO> page, ShareSetVO vo) {
	
		return shareSetDao.findPageByVo(page,vo);
	}

	public ShareSetBO findShareSetInfo() {
		List<ShareSetBO> findAll = shareSetDao.findAll();
		return findAll.isEmpty()?null:findAll.get(0);
	}

	public void saveOrUpdate(ShareSetBO bo) {
		
		//不允许邀请
		if(!bo.getIsAllowInvite()){
			shareSetDao.updateNoAllowInvite(bo.getId());
		}
		//不允许分享
		if(!bo.getIsAllowShare()){
			shareSetDao.updateNoALlowShare(bo.getId());
		}
		if(null != bo.getId()){
			//修改
			bo.setUpdateTime(new Date());
		}else {
			//新增
			bo.setCreateTime(new Date());
		}
		
		shareSetDao.save(bo);
	}
}
