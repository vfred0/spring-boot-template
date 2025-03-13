package com.gnoboa;

import com.gnoboa.configurations.jwt.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)
public class MarketVisitiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketVisitiaApplication.class, args);
	}

}
