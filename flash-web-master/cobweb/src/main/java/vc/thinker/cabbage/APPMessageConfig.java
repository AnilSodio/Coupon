package vc.thinker.cabbage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinco.messager.mobile.MobileMessageHandler;
import com.sinco.messager.mobile.jpush.JpushMobileMessageHandler;

@Configuration
@ConfigurationProperties(prefix="app")
public class APPMessageConfig {
	
	@Value("${app.member.appkey}")
	private String memberAppKey;
	
	@Value("${app.member.master.secret}")
	private String memberMasterSecret;
	
	@Value("${app.isProduct}")
	private Boolean isProduct = true;
	
	@Bean
	public MobileMessageHandler memberAppMessageHandler(){
		MobileMessageHandler handler=new JpushMobileMessageHandler(memberAppKey, memberMasterSecret, isProduct);
		return handler;
	}
}
