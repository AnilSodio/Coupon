package vc.thinker.cabbage.web.security;

import java.util.List;

import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.model.User;
import vc.thinker.web.security.SysPrincipal;

public class AdminPrincipal extends SysPrincipal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7780221186114180916L;
	
	private AdminBO admin;
	
	private List<PermissionBO> permissions;
	
	public AdminPrincipal(User user,AdminBO admin,String loginName, String host) {
		super(user, loginName,admin.getName(), host);
		this.admin=admin;
	}
	public List<PermissionBO> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionBO> permissions) {
		this.permissions = permissions;
	}
	public AdminBO getAdmin() {
		return admin;
	}
	public void setAdmin(AdminBO admin) {
		this.admin = admin;
	}
}
