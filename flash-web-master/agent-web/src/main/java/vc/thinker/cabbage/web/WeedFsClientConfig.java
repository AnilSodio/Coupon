package vc.thinker.cabbage.web;

import net.weedfs.client.WeedFSClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class WeedFsClientConfig {
	@Value("${fs.server.ip}")
	private String imageIp;
	@Value("${fs.server.port}")
	private String imagePort;

	@Bean
	public WeedFSClient imageFsClient(){
		WeedFSClient fsClient = new WeedFSClient(imageIp,imagePort);
		return fsClient;
	}

}
