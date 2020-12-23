package vc.thinker.cabbage.user.vo;

import com.sinco.dic.client.annotation.DicMappingModel;
import com.sinco.dic.client.annotation.DicNameMapping;

import vc.thinker.sys.bo.OfficeBO;

/**
 * 用于 admin find page
 * @author james
 *
 */
@DicMappingModel
public class AdminFindPageVO {
	
	private Long officeId;
	
	@DicNameMapping(codeField="officeId",type=OfficeBO.class)
	private String officeName;
	
	private Boolean isAdmin;
	
	private String loginName;
	
	private String name;
	
	private Long companyId;
	
	@DicNameMapping(codeField="companyId",type=OfficeBO.class)
	private String companyName;
	
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
