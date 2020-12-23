package vc.thinker.cabbage.web.security;

import java.util.List;

import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.model.User;
import vc.thinker.web.security.SysPrincipal;

public class SellerPrincipal extends SysPrincipal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7780221186114180916L;
	
	private SellerBO seller;
	
	private List<PermissionBO> permissions;
	
	public SellerPrincipal(User user,SellerBO seller,String loginName, String host) {
		super(user, loginName,seller.getSellerName(), host);
		this.seller=seller;
	}
	public List<PermissionBO> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionBO> permissions) {
		this.permissions = permissions;
	}
	public SellerBO getSeller() {
		return seller;
	}
	public void setSeller(SellerBO seller) {
		this.seller = seller;
	}
}
