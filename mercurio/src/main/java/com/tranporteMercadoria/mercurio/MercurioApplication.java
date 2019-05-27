package com.tranporteMercadoria.mercurio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MercurioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercurioApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}
