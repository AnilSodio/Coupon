package vc.thinker.cabbage.se.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 充电柜实时状态
 * @author james
 *
 */
@Document(collection = "cabinet_status")
@TypeAlias("cabinetStatus")
public class CabinetStatus {
	
	public static final String F_LAST_HEART_BEAT="lastHeartbeat";
	
	public static final String F_CID="cid";
	
	public static final String F_CABINET_CODE="cabinetCode";
	
	public static final String F_SYS_CODE="sysCode";
	
	public static final String F_LAST_UPDATE_TIME="lastUpdateTime";
	
	public static final String F_SERVICE_CODE="serviceCode";
	
	public static final String F_SELLER_ID="sellerId";
	
	public static final String F_LOCATION="location";
	
	public static final String F_LOCATION_DETAILS="locationDetails";
	
	public static final String F_LOCATION_CITY="locationCity";
	
	public static final String F_LOCATION_AREA="locationArea";
	
	public static final String F_ONLINE="online";

	public static final String F_RUN_STATUS = "runStatus";
	
	public static final String F_IDLE_POSITION_NUM = "idlePositionNum";
	
	public static final String F_EXIST_POSITION_NUM = "existPositionNum";
	
	public static final String F_CHANNEL_STATUS_LIST= "channelStatusList";
	
	public static final String F_POSITIONTOTAL="positionTotal";
	
	public static final String F_STATUS = "status";

	//运行状态
	public static enum RunStatus{
		normal, //正常
		stop    //停用
	}
	
	@Id
	private String id;

	//充电柜cid
	private Long cid;
	
	private Long sellerId;
	
	//机柜 code
	@Indexed(name="u_cabinetCode",unique=true)
	private String cabinetCode;
	
	//机柜 sysCode
	@Indexed(name="u_sys_code")
	private String sysCode;
	
	/**
	 * 使用 火星坐标 存储
	 */
	@GeoSpatialIndexed(name="index_lastLocation",type=GeoSpatialIndexType.GEO_2DSPHERE )
	private Point location;
	
	//位置的详情
	private String locationDetails;
	
	//最后心跳
	private Date lastHeartbeat;
	
	//数据最后更新
	private Date lastUpdateTime;
	
	//是否在线
	private Boolean online;
	
	//空闲的位置
	private Integer idlePositionNum;
	
	//存在的电池数
	private Integer existPositionNum;
	
	//总位置数
	private Integer positionTotal;
	
	//服务器标识
	private String serviceCode;
	
	private RunStatus runStatus;
	
	//通道状态
	private List<ChannelStatus> channelStatusList;
	
	//锁固件版本
	private String version;
	
	/*
	 * 
	1：苹果线
	
	2：苹果线/micro-usb，
	
	3：TYPE C线  
	
	4：三合一线      
	 */
	private Integer batteryType1Count;
	
	private Integer batteryType2Count;
	
	private Integer batteryType3Count;
	
	private Integer batteryType4Count;
	
	private Integer status;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Integer getBatteryType1Count() {
		return batteryType1Count;
	}

	public void setBatteryType1Count(Integer batteryType1Count) {
		this.batteryType1Count = batteryType1Count;
	}

	public Integer getBatteryType2Count() {
		return batteryType2Count;
	}

	public void setBatteryType2Count(Integer batteryType2Count) {
		this.batteryType2Count = batteryType2Count;
	}

	public Integer getBatteryType3Count() {
		return batteryType3Count;
	}

	public void setBatteryType3Count(Integer batteryType3Count) {
		this.batteryType3Count = batteryType3Count;
	}

	public Integer getBatteryType4Count() {
		return batteryType4Count;
	}

	public void setBatteryType4Count(Integer batteryType4Count) {
		this.batteryType4Count = batteryType4Count;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getExistPositionNum() {
		return existPositionNum;
	}

	public void setExistPositionNum(Integer existPositionNum) {
		this.existPositionNum = existPositionNum;
	}

	public List<ChannelStatus> getChannelStatusList() {
		return channelStatusList;
	}

	public void setChannelStatusList(List<ChannelStatus> channelStatusList) {
		this.channelStatusList = channelStatusList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCabinetCode() {
		return cabinetCode;
	}

	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public Date getLastHeartbeat() {
		return lastHeartbeat;
	}

	public void setLastHeartbeat(Date lastHeartbeat) {
		this.lastHeartbeat = lastHeartbeat;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Integer getIdlePositionNum() {
		return idlePositionNum;
	}

	public void setIdlePositionNum(Integer idlePositionNum) {
		this.idlePositionNum = idlePositionNum;
	}

	public Integer getPositionTotal() {
		return positionTotal;
	}

	public void setPositionTotal(Integer positionTotal) {
		this.positionTotal = positionTotal;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public RunStatus getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(RunStatus runStatus) {
		this.runStatus = runStatus;
	}
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public static class ChannelStatus{
		//是否锁住
		private boolean lock;
		//左传感器在 原点
		private boolean leftOrigin;
		//右传感器在 原点
		private boolean rightOrigin;
		//通道按键按
		private Boolean isButton;
		//读到id
		private Boolean isReadId;
		
		private String status;
		
		private String pbCode;
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public boolean isLock() {
			return lock;
		}
		public void setLock(boolean lock) {
			this.lock = lock;
		}
		public boolean isLeftOrigin() {
			return leftOrigin;
		}
		public void setLeftOrigin(boolean leftOrigin) {
			this.leftOrigin = leftOrigin;
		}
		public boolean isRightOrigin() {
			return rightOrigin;
		}
		public void setRightOrigin(boolean rightOrigin) {
			this.rightOrigin = rightOrigin;
		}
		public Boolean getIsButton() {
			return isButton;
		}
		public void setIsButton(Boolean isButton) {
			this.isButton = isButton;
		}
		public Boolean getIsReadId() {
			return isReadId;
		}
		public void setIsReadId(Boolean isReadId) {
			this.isReadId = isReadId;
		}
		public String getPbCode() {
			return pbCode;
		}
		public void setPbCode(String pbCode) {
			this.pbCode = pbCode;
		}
	}
}