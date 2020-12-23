package vc.thinker.cabbage.web.security;

import java.util.List;

import vc.thinker.cabbage.user.bo.AdminBO;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.bo.SellerBO;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.model.User;
import vc.thinker.web.security.SysPrincipal;

public class AgentPrincipal extends SysPrincipal{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7780221186114180916L;
	
	private AgentBO agent;
	
	private List<PermissionBO> permissions;
	
	public AgentPrincipal(User user,AgentBO agent,String loginName, String host) {
		super(user, loginName,agent.getAgentName(), host);
		this.agent=agent;
	}
	public List<PermissionBO> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<PermissionBO> permissions) {
		this.permissions = permissions;
	}
	public AgentBO getAgent() {
		return agent;
	}
	public void setAgent(AgentBO agent) {
		this.agent = agent;
	}
	
	
	
}
