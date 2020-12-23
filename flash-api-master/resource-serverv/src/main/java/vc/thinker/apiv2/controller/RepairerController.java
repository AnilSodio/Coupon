package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import vc.thinker.apiv2.bo.APICabinetBO;
import vc.thinker.apiv2.bo.APIChannelStatusBO;
import vc.thinker.apiv2.bo.APIPortableBatteryBO;
import vc.thinker.apiv2.bo.APIRepairerBO;
import vc.thinker.apiv2.http.response.SimpleResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.Responses;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.biz.exception.ServiceException;
import vc.thinker.cabbage.common.SysQrCodeUtil;
import vc.thinker.cabbage.se.CabinetRemoteHandle;
import vc.thinker.cabbage.se.CabinetService;
import vc.thinker.cabbage.se.PortableBatteryConstatns;
import vc.thinker.cabbage.se.PortableBatteryService;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.user.bo.RepairerBO;
import vc.thinker.cabbage.user.service.RepairerService;
import vc.thinker.cabbage.util.CabinetTypeEnum;
import vc.thinker.oauth.AuthContants;
import vc.thinker.opensdk.ReturnMsg;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;
import vc.thinker.opensdk.powerbank.relink.RelinkSlot;
import vc.thinker.utils.RedisCacheUtils;

@RestController
@RequestMapping(value = "/api/repairer" , produces = APPLICATION_JSON_VALUE)
@Api(value = "维保用户相关API",description="维保用户相关API")
@Validated
public class RepairerController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RepairerService repairerService;
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private CabinetRemoteHandle cabinetRemoteHandle;
	
	@Autowired
	private PortableBatteryService portableBatteryService;
	
	@Autowired
	private CabinetService cabinetService;
	
	@Autowired
	private RedisCacheUtils redisCacheUtils;
	
	@Autowired
	@Resource(name="relinkSDK")
	private RelinkOpenSDK relinkSDK;
	
	@PreAuthorize("hasRole('"+AuthContants.ROLE_REPAIRER_USER+"')")
	@RequestMapping(value = "/detail", method = { RequestMethod.GET})
	@ApiOperation(value = "维保用户信息", notes = "获取维保用户信息详情")
	public ResponseEntity<SingleResponse<APIRepairerBO>> detail(
			@UserAuthentication User user){
		Long uid=Long.parseLong(user.getUsername());
		SingleResponse<APIRepairerBO> resp = new SingleResponse<APIRepairerBO>();
		RepairerBO repairerBO = repairerService.findOne(uid);
		APIRepairerBO apiBO = mapper.map(repairerBO, APIRepairerBO.class);
		resp.setItem(apiBO);
		resp.setSuccess(true);
		return Responses.ok(resp);
	}
	
	@RequestMapping(value = "/update_head_img", method = { RequestMethod.POST })
	@PreAuthorize("hasRole('"+AuthContants.ROLE_REPAIRER_USER+"')")
	@ApiOperation(value = "修改用户头像")
	public ResponseEntity<SingleResponse<APIRepairerBO>> updateHeadImg(HttpServletRequest request,
			@UserAuthentication User user, 
			@ApiParam(value = "图像", required = true) @RequestParam(value="headImg") String headImg
			){
		Long uid=Long.parseLong(user.getUsername());
		SingleResponse<APIRepairerBO> resp = new SingleResponse<APIRepairerBO>();
		repairerService.updateHeadImg(uid, headImg);
		RepairerBO opNew =repairerService.findOne(uid);
		resp.setItem(mapper.map(opNew, APIRepairerBO.class));
		return Responses.ok(resp);
	}
	

	@PreAuthorize("hasRole('"+AuthContants.ROLE_REPAIRER_USER+"')")
	@RequestMapping(value = "/cabinet_profile", method = { RequestMethod.GET })
	@ApiOperation(value = "获取充电柜详情信息")
	public SingleResponse<APICabinetBO> findCabinetProfile(
			@UserAuthentication User user,
			HttpServletRequest request,
			@ApiParam(value = "二维码", required = true) @RequestParam("qrCode") String qrCode){
		String sysCode=SysQrCodeUtil.getSysCode(qrCode);
		
		SingleResponse<APICabinetBO> resp = new SingleResponse<APICabinetBO>();
		
		CabinetBO cabinet=cabinetService.findDetailsBySysCode(sysCode);
		if(cabinet != null){
//			SellerBO seller=sellerService.findById(cabinet.getSellerId());
//			if(seller == null){
//				resp.setErrorInfo("406", "未投放的机柜");
//				return resp;
//			}
			resp.setItem(mapper.map(cabinet, APICabinetBO.class));
//			resp.getItem().setSeller(mapper.map(seller, APISellerBO.class));
			List<PortableBatteryBO> portableBatteryList=
					portableBatteryService.findBycabinetId(cabinet.getId());
			List<APIChannelStatusBO> channelStatusList= resp.getItem().getChannelStatusList();
			if(channelStatusList == null){
				channelStatusList=new ArrayList<>();
			}
			for (int i = 0; i < channelStatusList.size(); i++) {
				APIChannelStatusBO channelStatus=channelStatusList.get(i);
				channelStatus.setChannel(i+1);
				if(null != channelStatus.getIsReadId() && channelStatus.getIsReadId()){
					for (PortableBatteryBO pb : portableBatteryList) {
						if(pb.getCabinetChannel() == i+1 && PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET == pb.getLocationType()){
							channelStatus.setPortableBattery(mapper.map(pb, APIPortableBatteryBO.class));
						}
					}
				}
			}
		}
		return resp;
	}
	
	private static final String KEY_SYS_OUT="sys_out:";
	
	private static final Integer SYS_OUT_SECONDS=5;
	
	@RequestMapping(value = "/out", method = { RequestMethod.POST })
	@PreAuthorize("hasRole('"+AuthContants.ROLE_REPAIRER_USER+"')")
	@ApiOperation(value = "人工借出")
	@ApiResponses(value = {@ApiResponse(code = 406, message = "业务操作错误")})
	public ResponseEntity<SimpleResponse> out(
			@UserAuthentication User user,
			@ApiParam(value = "二维码", required = true) @RequestParam("qrCode") String qrCode,
			@ApiParam(value = "通道", required = true) @RequestParam("channel") Integer channel){
		Long uid=Long.parseLong(user.getUsername());
		SimpleResponse resp = new SimpleResponse();
		resp.setSuccess(true);
		String sysCode=SysQrCodeUtil.getSysCode(qrCode);
		String cacheKey=new StringBuilder(KEY_SYS_OUT).append(":").append(uid).append(":").append(sysCode).toString();
		Long ttl=redisCacheUtils.ttl(cacheKey);
		
		if(ttl > 0){
			resp.setErrorInfo("406","操作过快，请"+ttl+"秒后再重试");
			return Responses.ok(resp);
		}
		try {
			/*
			 * 1.relink设备直接http调用弹出
			 * 2.云充吧/伏特加设备使用tcp指令
			 * 3.云充吧大设备
			 */
			CabinetBO bo=cabinetService.findBySysCode(sysCode);
			if (bo.getTypeId() == CabinetTypeEnum.RELINK.getCode()
					|| bo.getTypeId() == CabinetTypeEnum.RELINKB.getCode()
					|| bo.getTypeId() == CabinetTypeEnum.RELINKC.getCode()) {
				this.outRelink(bo.getCabinetCode(), channel);
			} else if (bo.getTypeId() == CabinetTypeEnum.FTJ.getCode() 
					|| bo.getTypeId() == CabinetTypeEnum.YCB.getCode() ) {
				cabinetRemoteHandle.sysOut(sysCode, channel);
				redisCacheUtils.set(cacheKey, sysCode, SYS_OUT_SECONDS);
			} else {
				
			}
		} catch (ServiceException e) {
			resp.setErrorInfo("406", e.getMessage());
			return Responses.ok(resp);
		}
		return Responses.ok(resp);
	}
	

	/**
	 * 
	 * @param sn
	 * @param slot
	 * @return
	 */
    private JSONObject outRelink(String sn, int slot){
    	JSONObject reuslt=new JSONObject();
    	ReturnMsg<RelinkSlot> ret = relinkSDK.callPop(sn,slot);
    	if (ret.getRetCode() == 0) {
        	reuslt.put("errcode", 0);
        	reuslt.put("errmsg", "remove battery successfully");   
		} else {
	    	reuslt.put("errcode", ret.getRetCode() );
	    	reuslt.put("errmsg", ret.getRetMsg());   
		}
    	 	
    	return reuslt;
    }
	
}
