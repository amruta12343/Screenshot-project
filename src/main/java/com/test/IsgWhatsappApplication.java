package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IsgWhatsappApplication {

	public static void main(String[] args) {
		// SpringApplication.run(IsgWhatsappApplication.class, args);
		ApplicationContext contexto = new SpringApplicationBuilder(IsgWhatsappApplication.class)
				.web(WebApplicationType.NONE).headless(false)
				// .bannerMode(Banner.Mode.OFF)
				.run(args);
	}

}
