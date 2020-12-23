package vc.thinker.cabbage.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.data.core.Page;

import vc.thinker.cabbage.sys.bo.SysMessageBO;
import vc.thinker.cabbage.sys.bo.SysMessageLogBO;
import vc.thinker.cabbage.sys.dao.SysMessageDao;
import vc.thinker.cabbage.sys.dao.SysMessageLogDao;
import vc.thinker.cabbage.sys.vo.SysMessageLogVO;
import vc.thinker.cabbage.user.CommonConstants;
import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.dao.MemberDao;
import vc.thinker.cabbage.common.MyPage;
import vc.thinker.utils.CommUtil;

@Service
@Transactional
public class SysMessageLogService {

	private Logger logger = LoggerFactory.getLogger(SysMessageLogService.class);
	
	@Autowired
	private SysMessageDao sysMessageDao;
	
	@Autowired
	private SysMessageLogDao sysMessageLogDao;
	
//	@Autowired
//	private RepairerDao repairerDao;
	
//	@Autowired
//	private AgentDao agentDao;
	
	@Autowired
	private MemberDao memberDao;

	/**
	 * 分页查询
	 * @param page
	 * @param vo
	 * @return
	 */
	public List<SysMessageLogBO> findPageByVo(MyPage<SysMessageLogBO> page, SysMessageLogVO vo) {
		return sysMessageLogDao.findPageByVo(page,vo);
	}

	/**
	 * 消息发送
	 * @param bo
	 */
	public void sendSysMessage(SysMessageLogBO bo) {
		//保存系统消息
		Boolean sendAll = bo.getIsSendAll();
		
		List<String> uidList = new ArrayList<String>();
		
		if(sendAll){
			List<MemberBO> user_list = new ArrayList<MemberBO>();
			if(1 == bo.getSendType()){
				//会员
				user_list = memberDao.findAllVipOrNotVip(true);
			}
			if(2 == bo.getSendType()){
				//非会员
				user_list = memberDao.findAllVipOrNotVip(false);
			}
			
			if(3 == bo.getSendType()){
				//全部用户
				user_list = memberDao.findAllOnlyUid();
			}
			
			for (MemberBO u : user_list) {
				uidList.add(CommUtil.null2String(u.getUid()));
			}
		}else {
			uidList = Arrays.asList(bo.getToUserIds().split(","));
		}
		
		bo.setSendType(CommonConstants.USER_TYPE_2);
		sysMessageLogDao.save(bo);
		
		
		List<SysMessageBO> listMessage = new ArrayList<SysMessageBO>();
		
		if(null != uidList && uidList.size()>0){
			
			Page<String> page=new Page<>();
			page.setTotal(uidList.size());
			page.setPageSize(100);
			
			logger.info("@@@@共有"+uidList.size()+"条数据，循环次数为："+page.getTotalPages());
			
			//循环操作
			for(int i=1;i<= page.getTotalPages();i++){
				page.setPageNumber(i);
				//开始循环
				long size=page.getPageNumber() * page.getPageSize();
				size = size > page.getTotalElements() ? page.getTotalElements() : size;
				for(int j=page.getOffset();j<size;j++){
					
					SysMessageBO message = new SysMessageBO();
					message.setContent(bo.getContent());
					message.setFromUserId(bo.getFromUserId());
					message.setImageTextId(bo.getImageTextId());
					message.setToUserId(CommUtil.null2Long(uidList.get(j)));
					message.setSendTime(bo.getSendTime());
					message.setIsImageText(bo.getIsImageText());
					message.setIsPlatform(bo.getIsPlatform());
					message.setIsRead(false);
					message.setLogId(bo.getId());
					message.setToUserType(bo.getSendType()+"");
					
					listMessage.add(message);
				}
				
				sysMessageDao.insertByList(listMessage);
				listMessage.clear();
			}
		}
	}

	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	public SysMessageLogBO findOne(Long id) {
		return sysMessageLogDao.findOne(id);
	}
	
}
