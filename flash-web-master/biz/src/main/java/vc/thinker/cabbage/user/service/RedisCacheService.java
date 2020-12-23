package vc.thinker.cabbage.user.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sinco.common.utils.SerializeUtil;
import com.sinco.mybatis.dal.model.BaseModel;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/***
 * 缓存service
 * 
 * @author
 *
 */

@Service
public class RedisCacheService {

	@Resource(name = "cabbageRedisPool")
	private JedisPool bizRedisPool;
	
	@SuppressWarnings("hiding")
	public <T extends BaseModel> void setObject(String key, T t, int seconds) throws Exception {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.setex(key.getBytes(), seconds, SerializeUtil.serialize(t));
		} finally {
			bizRedisPool.returnResource(jedis);
		}
	}
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends BaseModel> T getObject(String key, Class<T>  obj) throws Exception{
		Jedis jedis = bizRedisPool.getResource();
		T t = obj.newInstance();
		try {
			if (jedis.get(key.getBytes()) != null) 
				t =  (T) SerializeUtil.unserialize(jedis.get(key.getBytes()));
			else
				return null;
		} finally {
			bizRedisPool.returnResource(jedis);
		}
		return t;
	}
	public void delObject(String key){
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.del(key.getBytes());
		} finally {
			bizRedisPool.returnResource(jedis);
		}
	}
	
	public void setSmsCode(Long userId, String code, String mobile) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile + "_count", 20 * 60, "0");
			jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile, 20 * 60, code);
		} finally {
			bizRedisPool.returnResource(jedis);
		}
		
	}
	public void setSmsCode(Long userId, String code, String mobile,int minute) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile + "_count", minute * 60, "0");
			jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile, minute * 60, code);
		} finally {
			bizRedisPool.returnResource(jedis);
		}
	}

	public String getSmsCode(Long userId, String mobile) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile + "_count", 10 * 60, "0");
			String count = jedis.get("USER_SMS_CODE_" + userId + "_" + mobile + "_count");
			if (StringUtils.isNotBlank(count)) {
				int nextCount = Integer.parseInt(count) + 1;
				if (nextCount >= 10) {
					jedis.del("USER_SMS_CODE_" + userId + "_" + mobile + "_count");
					jedis.del("USER_SMS_CODE_" + userId + "_" + mobile);
				}
				jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile + "_count", 10 * 60, String.valueOf(nextCount));
			} else {
				jedis.setex("USER_SMS_CODE_" + userId + "_" + mobile + "_count", 10 * 60, "0");
			}

			String code = jedis.get("USER_SMS_CODE_" + userId + "_" + mobile);
			return code;
		} finally {
			bizRedisPool.returnResource(jedis);
		}
	}
	/**
	 * 删除验证码
	 * @param userId
	 * @param mobile
	 */
	public void delSmsCode(Long userId, String mobile) {
		Jedis jedis = bizRedisPool.getResource();
		try {
			jedis.del("USER_SMS_CODE_" + userId + "_" + mobile + "_count");
			jedis.del("USER_SMS_CODE_" + userId + "_" + mobile);
		} finally {
			bizRedisPool.returnResource(jedis);
		}
	}
}
