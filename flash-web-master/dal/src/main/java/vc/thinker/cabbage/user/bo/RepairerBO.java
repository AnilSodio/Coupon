package vc.thinker.cabbage.user.bo;


import com.sinco.dic.client.annotation.DicNameMapping;

import vc.thinker.cabbage.user.model.Repairer;
import vc.thinker.sys.bo.DicAreaBO;
/**
 * 
 * BO 用于返回数据
 *
 */
/**
 * @author thinker
 *
 */
public class RepairerBO extends Repairer{

private static final long serialVersionUID = 2435571951327350237L;
	
	@DicNameMapping(codeField="areaId",type=DicAreaBO.class,isFull=true,mergeChar="")
	private String area;
	
	//公司名称
	private String companyName;
	//机构名称
	private String officeName;
	
	private Long companyId;
	
	private Long officeId;
	
	private String password;
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}