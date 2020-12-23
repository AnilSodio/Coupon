package vc.thinker.apiv2;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sinco.messager.MessageHandler;
import com.sinco.messager.sms.SMSMessageAlidayuHandler;
import com.sinco.messager.sms.SMSMessageDevHandler;
import com.sinco.messager.sms.SmsMessageAliyunInternationalHandler;
import com.sinco.messager.sms.turbo.TurboMessageHandler;

import redis.clients.jedis.JedisPool;
import vc.thinker.cabbage.common.SmsHandlerType;
import vc.thinker.oauth.VerifyCodeUtil;
import vc.thinker.sys.utils.MsgTools;
import vc.thinker.utils.RedisCacheUtils;

@Configuration
public class SMSMessagerConfig {

	@Value("${sms.handler.type}")
	private String accountType;
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
	public MessageHandler messageHandler(){
		if(StringUtils.isBlank(accountType)) {
			return  new SMSMessageAlidayuHandler( jedisPool, accountSid,  accountToken);
		}
		return new TurboMessageHandler(accountSid, accountToken);
	}
	
	@Bean(name="verifyCodeUtil")
	public VerifyCodeUtil verifyCodeUtil(){
		return new VerifyCodeUtil();
	}

	@Bean
	public MsgTools msgTools(MessageHandler messageHandler){
		MsgTools msgTools = new MsgTools();
		msgTools.setSmsHandler(messageHandler);
		msgTools.setCacheUtils(redisCacheUtils);
		return msgTools;
	}
}
