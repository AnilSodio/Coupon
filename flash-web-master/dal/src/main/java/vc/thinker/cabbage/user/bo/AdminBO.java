package vc.thinker.cabbage.user.bo;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sinco.common.utils.Collections3;
import com.sinco.office.excel.annotation.ExcelField;

import vc.thinker.cabbage.user.model.Admin;
import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.UserAccount;
/**
 * 
 * BO 用于返回数据
 *
 */
public class AdminBO extends Admin {
	
//	private static final Logger log=LoggerFactory.getLogger(UserBO.class);
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	private Office company;	// 归属公司
	
	private Office office;	// 归属部门
	
	private String dataScope; 
	
	private UserAccount userAccount;//用户账号
	
	private String loginName;
	
	private String userStatus;
	
	private String areaId;
	
	private String name;
	
	private String mobile;
	
	private Long companyId;
	
	private Long officeId;
	
	private List<RoleBO> roleList = Lists.newArrayList(); // 拥有角色列表
	
	private List<OfficeBO> officeList = Lists.newArrayList(); // 按明细设置数据范围
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@NotNull(message="归属公司不能为空")
	@ExcelField(title="归属公司", align=2, sort=20)
	public Office getCompany() {
		return company;
	}
	

	public void setCompany(Office company) {
		this.company = company;
	}
	
//	@ExcelField(title="拥有角色", align=1, sort=800, fieldType=RoleListType.class)
	@ExcelField(title="拥有角色", align=1, sort=800)
	public List<RoleBO> getRoleList() {
		return roleList;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setRoleList(List<RoleBO> roleList) {
		this.roleList = roleList;
	}	

	@JsonIgnore
	public List<String> getRoleIdList() {
		List<String> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId().toString());
		}
		return roleIdList;
	}

	@JsonIgnore
	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			RoleBO role = new RoleBO();
			role.setId(Long.parseLong(roleId));
			roleList.add(role);
		}
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	@JsonIgnore
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}
	
	public List<OfficeBO> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<OfficeBO> officeList) {
		this.officeList = officeList;
	}

	@JsonIgnore
	public List<String> getOfficeIdList() {
		List<String> officeIdList = Lists.newArrayList();
		for (Office office : officeList) {
			officeIdList.add(String.valueOf(office.getId()));
		}
		return officeIdList;
	}

	@JsonIgnore
	public void setOfficeIdList(List<String> officeIdList) {
		officeList = Lists.newArrayList();
		for (String officeId : officeIdList) {
			OfficeBO office = new OfficeBO();
			office.setId(Long.valueOf(officeId));
			officeList.add(office);
		}
	}

	@JsonIgnore
	public String getOfficeIds() {
		List<String> nameIdList = Lists.newArrayList();
		for (Office office : officeList) {
			nameIdList.add(String.valueOf(office.getId()));
		}
		return StringUtils.join(nameIdList, ",");
	}
	
	@JsonIgnore
	public void setOfficeIds(String officeIds) {
		officeList = Lists.newArrayList();
		if (officeIds != null){
			String[] ids = StringUtils.split(officeIds, ",");
			for (String officeId : ids) {
				OfficeBO office = new OfficeBO();
				office.setId(Long.valueOf(officeId));
				officeList.add(office);
			}
		}
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
}