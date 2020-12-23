package vc.thinker.cabbage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import vc.thinker.cabbage.core.config.DatabaseConfig;
import vc.thinker.cabbage.core.config.DicConfig;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;
import vc.thinker.pay.CabbagePayConfig;
import vc.thinker.utils.SpringContextHolder;

@SpringBootApplication
@ComponentScan(basePackages = { "vc.thinker.**" })
@EnableAutoConfiguration(exclude={FreeMarkerAutoConfiguration.class})
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@ImportAutoConfiguration(value={DatabaseConfig.class,DicConfig.class,CabbagePayConfig.class})
public class JobStart{
	
	public static void main(String[] args) {
		SpringApplication.run(JobStart.class, args);
	}
	
	@Value("${iot.paas.id}")
	private String paasId;
	
	@Value("${iot.paas.url}")
	private String paasUrl;
	
	@Bean(name="relinkSDK")
	public RelinkOpenSDK relinkOpenSDK(){
		return new RelinkOpenSDK(paasId, paasUrl);
	}
	
	@Bean
	@Lazy(false)
	public vc.thinker.utils.SpringContextHolder springContextHolder(){
		return new SpringContextHolder();
	}
}
