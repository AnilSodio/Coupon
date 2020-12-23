package vc.thinker.cabbage.sys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import vc.thinker.cabbage.money.util.DateTimeUtils;
import vc.thinker.cabbage.sys.SysMessageConstants;
import vc.thinker.cabbage.sys.bo.ImageTextBO;
import vc.thinker.cabbage.sys.dao.ImageTextDao;
import vc.thinker.cabbage.sys.model.ImageTextExample;
import vc.thinker.cabbage.sys.vo.ImageTextVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class ImageTextService {

	private Logger logger = LoggerFactory.getLogger(ImageTextService.class);
	
	@Autowired
	private ImageTextDao imageTextDao;

	public List<ImageTextBO> findPageByVo(MyPage<ImageTextBO> page, ImageTextVO vo) {
		return imageTextDao.findPageByVo(page,vo);
	}

	public ImageTextBO findOne(Long id) {
		return imageTextDao.findOne(id);
	}

	/**
	 * 创建广告图文
	 * @param bo
	 */
	public void createAdImageText(ImageTextBO bo){
		
		//处理开始时间
		if(!StringUtils.isEmpty(bo.getStartDateString())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				bo.setStartDate(sdf.parse(bo.getStartDateString()));
			} catch (ParseException e) {
				logger.info("日期字符串转化出错！");
			}
		}else {
			bo.setStartDate(new Date());
		}
		
		//处理结束时间
		if(!StringUtils.isEmpty(bo.getInvalidDateString())){
			try {
				bo.setInvalidDate(DateTimeUtils.getAppoint59SecondDate(bo.getInvalidDateString()));
			} catch (ParseException e) {
				logger.info("新增首页广告时，时间转换错误！");
			}
		}
	
		bo.setStatus(SysMessageConstants.IMATE_TEXT_STATUS_1);
		bo.setCreateTime(new Date());
		
		imageTextDao.save(bo);
	}
	
	/**
	 * 新增或者修改图文
	 * @param bo
	 */
	public void saveOrUpdate(ImageTextBO bo) {
		
		if(null != bo.getId()){
			bo.setUpdateTime(new Date());
		}else {
			bo.setCreateTime(new Date());
			if(null != bo.getStatus() && SysMessageConstants.IMATE_TEXT_STATUS_2.equals(bo.getStatus())){
				if(!StringUtils.isEmpty(bo.getStartDateString())){
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						bo.setStartDate(sdf.parse(bo.getStartDateString()));
					} catch (ParseException e) {
						logger.info("日期字符串转化出错！");
					}
				}else {
					bo.setStartDate(new Date());
				}
			}
		}
		
		if(!StringUtils.isEmpty(bo.getInvalidDateString())){
			try {
				bo.setInvalidDate(DateTimeUtils.getAppoint59SecondDate(bo.getInvalidDateString()));
			} catch (ParseException e) {
				logger.info("新增首页广告时，时间转换错误！");
			}
		}
		
		
		imageTextDao.save(bo);
	}

	public void delete(Long id) {
		imageTextDao.delete(id);
	}

	public List<ImageTextBO> findAll() {
		return imageTextDao.findAll();
	}

	/**
	 * 根据状态和类型进行查找
	 * @param status 状态
	 * @param type 类型
	 * @return
	 */
	public List<ImageTextBO> findbyStatusAndType(Integer status, Integer type) {
		return imageTextDao.findbyStatusAndType(status,type);
	}

	/**
	 * 停用 或者启用
	 * @param id
	 * @param status
	 */
	public void stopOrStart(Long id, Integer status) {
		
		ImageTextBO up_bo = new ImageTextBO();
		
		up_bo.setId(id);
		up_bo.setStatus(status);
		
		if(SysMessageConstants.IMATE_TEXT_STATUS_2 == status){
			up_bo.setStartDate(new Date());
		}
		
		up_bo.setUpdateTime(new Date());
		imageTextDao.save(up_bo);
	}

	/**
	 * 查询所有已生效图文
	 * @return
	 */
	public List<ImageTextBO> findAllEffective() {
		ImageTextExample example = new ImageTextExample();
		example.createCriteria().andStatusEqualTo(SysMessageConstants.IMATE_TEXT_STATUS_2);
		return imageTextDao.selectByExample(example);
	}
	
}
