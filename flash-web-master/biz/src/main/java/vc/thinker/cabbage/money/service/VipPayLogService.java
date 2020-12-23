package vc.thinker.cabbage.money.service;


import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import vc.thinker.cabbage.money.util.DateTimeUtils;
import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.dao.VipPayLogDao;
import vc.thinker.cabbage.user.vo.VipPayLogVO;
import vc.thinker.cabbage.common.MyPage;

@Service
@Transactional
public class VipPayLogService {

	private Logger logger = LoggerFactory.getLogger(VipPayLogService.class);
	
	@Autowired
	private VipPayLogDao vipPayLogDao;

	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 */
	public List<VipPayLogBO> findPageByVo(MyPage<VipPayLogBO> page, VipPayLogVO vo) {
		try{
			if(!StringUtils.isEmpty(vo.getStartTime())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				vo.setGtTime(sdf.parse(vo.getStartTime()));
			}
			if(!StringUtils.isEmpty(vo.getEndTime())){
				vo.setLtTime(DateTimeUtils.getAppoint59SecondDate(vo.getEndTime()));
			}
		}catch (Exception e) {
			logger.info("用户购买会员卡记录查询日期格式转换出错！");
		}
		return vipPayLogDao.findPageByVo(page,vo);
	}	
}
