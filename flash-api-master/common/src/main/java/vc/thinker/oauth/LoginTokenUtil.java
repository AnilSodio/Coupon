package vc.thinker.oauth;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/***
 * 缓存service
 * 
 * @author
 *
 */

@Component
public class LoginTokenUtil {

	@Resource(name = "cabbageRedisPool")
	private JedisPool bizRedisPool;
	
	public String makeThirdPartyCodeKey(String openId){
		return "USER_TP_CODE_"+ openId;
	}
	
	/**
	 * 设置第三方登入的code
	 * @param openId
	 * @param code
	 */
	public void setThirdPartyCode(String openId,String code) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.setex(makeThirdPartyCodeKey(openId),30,code);
		} finally {
			jedis.close();
		}
	}
	/**
	 * 得到第三方登入的code
	 * @param openId
	 */
	public String getThirdPartyCode(String openId) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			return jedis.get(makeThirdPartyCodeKey(openId));
		} finally {
			jedis.close();
		}
	}
	
	public void delThirdPartyCode(String openId) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.del(makeThirdPartyCodeKey(openId));
		} finally {
			jedis.close();
		}
	}
	
}
