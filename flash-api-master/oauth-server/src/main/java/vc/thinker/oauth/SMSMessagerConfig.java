package vc.thinker.oauth;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sinco.messager.MessageHandler;
import com.sinco.messager.sms.SMSMessageAlidayuHandler;
import com.sinco.messager.sms.SMSMessageDevHandler;

import redis.clients.jedis.JedisPool;
import vc.thinker.oauth.VerifyCodeUtil;
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

	@Bean(name="messageHandler")
	@Profile({"production","test"})
	public MessageHandler messageHandler(){
		return  new SMSMessageAlidayuHandler( jedisPool, accountSid,  accountToken);
	}
	
	@Bean(name="messageHandler")
	@Profile({"dev"})
	public MessageHandler devMessageHandler(){
		return  new SMSMessageDevHandler();
	}
	
	@Bean(name="verifyCodeUtil")
	@Profile({"production","test"})
	public VerifyCodeUtil verifyCodeUtil(){
		return new VerifyCodeUtil();
	}
	
	@Bean(name="verifyCodeUtil")
	@Profile({"dev"})
	public VerifyCodeUtil devVerifyCodeUtil(){
		return new VerifyCodeUtil(true);
	}

	@Bean
	public MsgTools msgTools(MessageHandler messageHandler){
		MsgTools msgTools = new MsgTools();
		msgTools.setSmsHandler(messageHandler);
		msgTools.setCacheUtils(redisCacheUtils);
		return msgTools;
	}
}
