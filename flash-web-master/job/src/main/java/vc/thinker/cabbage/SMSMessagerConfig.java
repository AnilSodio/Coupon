package vc.thinker.cabbage;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinco.messager.MessageHandler;
import com.sinco.messager.sms.SMSMessageAlidayuHandler;

import redis.clients.jedis.JedisPool;
import vc.thinker.sys.utils.MsgTools;
import vc.thinker.utils.RedisCacheUtils;

@Configuration
public class SMSMessagerConfig {

	
	@Value("${sms.handler.accountSid}")
	private String accountSid;
	@Value("${sms.handler.accountToken}")
	private String accountToken;
	
	@Autowired
	@Resource(name="cabbageRedisPool")
	private JedisPool jedisPool;
	
	@Autowired
	private RedisCacheUtils redisCacheUtils;

	@Bean
	public MessageHandler messageHandler(){
		return  new SMSMessageAlidayuHandler( jedisPool, accountSid,  accountToken);
	}

	@Bean
	public MsgTools msgTools(MessageHandler messageHandler){
		MsgTools msgTools = new MsgTools();
		msgTools.setSmsHandler(messageHandler);
		msgTools.setCacheUtils(redisCacheUtils);
		return msgTools;
	}
}
