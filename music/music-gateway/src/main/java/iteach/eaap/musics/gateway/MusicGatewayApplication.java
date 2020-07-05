package iteach.eaap.musics.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MusicGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicGatewayApplication.class, args);
	}

}
