package iteach.eaap.musics.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MusicManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicManagementApplication.class, args);
	}

}
