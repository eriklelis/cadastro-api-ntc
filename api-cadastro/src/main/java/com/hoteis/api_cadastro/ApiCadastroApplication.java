package com.hoteis.api_cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCadastroApplication.class, args);
	}

}
