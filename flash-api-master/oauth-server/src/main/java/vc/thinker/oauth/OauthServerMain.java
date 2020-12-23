package vc.thinker.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import vc.thinker.cabbage.core.config.DatabaseConfig;
import vc.thinker.cabbage.core.config.DicConfig;
import vc.thinker.lbs.BaiduLbsBiz;
import vc.thinker.lbs.LbsBiz;
import vc.thinker.opensdk.powerbank.relink.RelinkOpenSDK;

@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class,
		JpaRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages = { "vc.thinker.**" }, basePackageClasses = {vc.thinker.sys.service.UserAccountService.class, vc.thinker.sys.dao.UserAccountDao.class,
		vc.thinker.cabbage.user.dao.MemberDao.class})
@ImportAutoConfiguration(value={DatabaseConfig.class,DicConfig.class})
@EnableTransactionManagement
@EnableAsync
public class OauthServerMain {
	
	
	@Value("${lbs.baidu.ak}")
	private String baiduAk;
	
	@Value("${iot.paas.id}")
	private String paasId;
	
	@Value("${iot.paas.url}")
	private String paasUrl;
	
	@Bean(name="relinkSDK")
	public RelinkOpenSDK relinkOpenSDK(){
		return new RelinkOpenSDK(paasId, paasUrl);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OauthServerMain.class, args);
	}

	@Bean
	public LbsBiz lbsBiz(){
		return new BaiduLbsBiz(baiduAk);
	}
}
