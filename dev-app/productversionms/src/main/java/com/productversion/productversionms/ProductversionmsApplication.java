package com.productversion.productversionms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductversionmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductversionmsApplication.class, args);
	}

}
