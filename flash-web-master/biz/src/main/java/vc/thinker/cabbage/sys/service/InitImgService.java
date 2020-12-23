package vc.thinker.cabbage.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vc.thinker.cabbage.sys.bo.InitImgBO;
import vc.thinker.cabbage.sys.dao.InitImgDao;
import vc.thinker.cabbage.sys.vo.InitImgVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class InitImgService {

	@Autowired
	private InitImgDao initImgDao;

	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<InitImgBO> findPageByVo(MyPage<InitImgBO> page, InitImgVO vo) {
		return initImgDao.findPageByVo(page,vo);
	}

	/**
	 * 主键查询
	 * @param id 主键
	 * @return
	 */
	public InitImgBO findOne(Long id) {
		return initImgDao.findOne(id);
	}

	/**
	 * 修改或新增
	 * @param bo
	 */
	public void saveOrUpdate(InitImgBO bo) {
		
		if(null != bo.getId()) {
			//修改
			bo.setUpdateTime(new Date());
		}else {
			//新增
			bo.setCreateTime(new Date());
			bo.setIsDelete(false);
		}
		
		initImgDao.save(bo);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void delete(long id) {
		initImgDao.delete(id);
	}

	/**
	 * 查询未被删除的图片
	 * @param imgType 
	 * 	      CommonConstants.AD_TYPE_START_PAGE 启动页广告图片
	 * 		  CommonConstants.AD_TYPE_HOME_PAGE  首页广告图片
	 * @return
	 */
	public List<InitImgBO> findNormalImg(Integer imgType) {
		return initImgDao.findNormalImg(imgType);
	}
}
