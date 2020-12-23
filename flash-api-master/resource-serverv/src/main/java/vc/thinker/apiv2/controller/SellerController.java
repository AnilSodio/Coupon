package vc.thinker.apiv2.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import vc.thinker.apiv2.ResourceServerMain;
import vc.thinker.apiv2.bo.APISellerBO;
import vc.thinker.apiv2.http.response.ListResponse;
import vc.thinker.apiv2.http.response.SingleResponse;
import vc.thinker.apiv2.utils.MapperUtils;
import vc.thinker.apiv2.web.BaseController;
import vc.thinker.apiv2.web.Responses;
import vc.thinker.apiv2.web.UserAuthentication;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.cabbage.user.service.SellerService;

@RestController
@RequestMapping(value = "/api/seller/", produces = { APPLICATION_JSON_VALUE })
@Api(value = "服务商操作相关",description="服务商操作相关")
public class SellerController extends BaseController{
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private Mapper mapper;
	
	@RequestMapping(value = "/find_by_location", method = { RequestMethod.GET })
	@ApiOperation(value = "根据位置查找商户",notes = "根据位置查找商户",
			authorizations=@Authorization(value = ResourceServerMain.securitySchemaOAuth2Cc))
	public ResponseEntity<ListResponse<APISellerBO>> findByLocationAndDistance(
			@UserAuthentication User user,
			@ApiParam(value="经度",required=true) @RequestParam(value="x",required=true) Double x
			,@ApiParam(value="纬度",required=true) @RequestParam(value="y",required=true) Double y
			,@ApiParam(value="距离",required=true) @RequestParam(value="distance",required=true) Integer distance
//			,@ApiParam(value="坐标系类型,百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、WGS84") 
//			@RequestParam(value="pointType",defaultValue=PointUtil.GCJ02) String pointType
			){
		ListResponse<APISellerBO> resp = new ListResponse<>();
		resp.setSuccess(true);
		
		org.springframework.data.geo.Point p = new org.springframework.data.geo.Point(x,y);
		
		List<SellerBO> sellerList=sellerService.findNormalByLocation(p, distance);
		
		//过滤没有充电框的数据
		List<SellerBO> result=sellerList.stream().filter(e -> e.getExistCabinetNum() > 0).
				collect(Collectors.toList());
		
		List<SellerBO> resp_list = new ArrayList<SellerBO>();
		
		if(!result.isEmpty()){
			for(int i=0;i<result.size();i++){
				SellerBO seller = sellerService.findCabinetDetails(result.get(i).getUid());
//				if(seller.getBatteryType2Count() == 0 
//						&& seller.getBatteryType3Count() == 0 
//						&& seller.getBatteryType4Count() == 0){
//					result.remove(i);
//				}
				if(seller.getBatteryType2Count() != 0 
						|| seller.getBatteryType3Count() != 0
						|| seller.getBatteryType4Count() != 0){
					resp_list.add(result.get(i));
				}
			}
		}
		
		resp.setItems(MapperUtils.map(mapper, resp_list, APISellerBO.class));
		
		return Responses.ok(resp);
	}
	
	@RequestMapping(value = "/profile", method = { RequestMethod.GET })
	@ApiOperation(value = "获取商户信息",authorizations=@Authorization(value = ResourceServerMain.securitySchemaOAuth2Cc))
	public SingleResponse<APISellerBO> profile(
			@UserAuthentication User user,
			@ApiParam(value = "sellerId", required = true) @RequestParam("sellerId") Long sellerId){
		SingleResponse<APISellerBO> resp = new SingleResponse<APISellerBO>();
		SellerBO bo=sellerService.findCabinetDetails(sellerId);
		resp.setItem(mapper.map(bo, APISellerBO.class));
		return resp;
	}
	
}
