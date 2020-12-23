package vc.thinker.cabbage.web.security;

import java.util.List;

import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.model.User;
import vc.thinker.web.security.SysPrincipal;

public class RefereePrincipal extends SysPrincipal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7780221186114180916L;
	
	private RefereeBO referee;
	
	private List<PermissionBO> permissions;
	
	public RefereePrincipal(User user,RefereeBO referee,String loginName, String host) {
		super(user, loginName,referee.getRefereeName(), host);
		this.referee=referee;
	}
	public List<PermissionBO> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionBO> permissions) {
		this.permissions = permissions;
	}
	public RefereeBO getReferee() {
		return referee;
	}
	public void setReferee(RefereeBO referee) {
		this.referee = referee;
	}	
	
}
