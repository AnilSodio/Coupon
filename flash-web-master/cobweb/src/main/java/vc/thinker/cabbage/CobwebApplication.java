package vc.thinker.cabbage;


import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sinco.common.utils.Base64;
import com.sinco.common.utils.IPUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import vc.thinker.cabbage.core.config.DatabaseConfig;
import vc.thinker.cabbage.core.config.DicConfig;
import vc.thinker.cabbage.extension.SpringExtension;
import vc.thinker.lbs.BaiduLbsBiz;
import vc.thinker.lbs.LbsBiz;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;

/**
 * Tool to trigger messages passed to actors.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "vc.thinker.**" })
@EnableAutoConfiguration
@EnableMongoAuditing
@EnableTransactionManagement
@EnableWebMvc
@EnableAsync
@EnableScheduling
@ImportAutoConfiguration(value={DatabaseConfig.class,DicConfig.class})
public class CobwebApplication {
	
	private static final Logger log=LoggerFactory.getLogger(CobwebApplication.class);
	
	@Value("${tcp.port}")
	private int PORT = 6007;
	
	@Value("${command.push.type}")
	private String commandPushType;
	
	@Value("${lbs.baidu.ak}")
	private String lbsBaiduAk;
	
	@Value("${iot.paas.id}")
	private String paasId;
	
	@Value("${iot.paas.url}")
	private String paasUrl;
	
	@Bean(name="relinkSDK")
	public RelinkOpenSDK relinkOpenSDK(){
		return new RelinkOpenSDK(paasId, paasUrl);
	}
	
	private Base64 b64 = new Base64();
	
	/*
	 * 服务器编码
	 */
	public static String serviceCode;
	
    @Autowired
    private ApplicationContext applicationContext;
    
    @PostConstruct
    public void init(){
    	String host;
		try {
			host = IPUtil.getLocalIP() + ":" + PORT;
	    	serviceCode = b64.encode(host.getBytes());
	    	log.info("init serviceCode = [{}]",serviceCode);
		} catch (UnknownHostException e) {
			throw new RuntimeException("获取本机ip地址失败",e);
		}
    }
    
	@Bean
	public SpringExtension springExtension(){
		return new SpringExtension();
	}
	
    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
	
    /**
     * Actor system singleton for this application.
     */
    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
            .create("AkkaTaskProcessing", akkaConfiguration());
        
        // Initialize the application context in the Akka Spring Extension
        springExtension().initialize(applicationContext);
        return system;
    }
	
	@Bean
	public LbsBiz lbsBiz(){
		return new BaiduLbsBiz(lbsBaiduAk);
	}
	
    public static void main(String[] args) throws Exception {
    	SpringApplication.run(CobwebApplication.class, args);
    }
}
