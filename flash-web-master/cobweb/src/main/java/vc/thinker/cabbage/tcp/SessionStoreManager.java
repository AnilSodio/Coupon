package vc.thinker.cabbage.tcp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class SessionStoreManager {

    private Map<String, IoSession> sessionCache = new HashMap<String, IoSession>();


    public IoSession getSession(String imie) {
    	return sessionCache.get(imie);
    }

    public void setSession(String imie,IoSession session){
    	sessionCache.put(imie, session);
    }

    public void deleteSession(String imie){
    	sessionCache.remove(imie);
    }

    public void sendMessage(List<String> keys, Object message){
        for(String key : keys){
            IoSession session = getSession(key);

            if(session == null){
                return;
            }
            session.write(message);
        }
    }
}
