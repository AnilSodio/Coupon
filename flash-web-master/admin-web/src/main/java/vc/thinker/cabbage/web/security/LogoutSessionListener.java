package vc.thinker.cabbage.web.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * 会话监听
 * @author james
 *
 */
public class LogoutSessionListener extends SessionListenerAdapter{
	
    public void onExpiration(Session session) {//会话过期时触发  
    	//UserUtils.clearCacheMap((String) session.getId());
    }  
    
    public void onStop(Session session) {//退出/会话过期时触发  
    	//UserUtils.clearCacheMap((String) session.getId());
    }    
}
