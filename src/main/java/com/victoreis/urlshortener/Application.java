package com.victoreis.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
		.directory("./.env")
		.filename(".env")
		.load();
		System.setProperty("PORT", dotenv.get("PORT", dotenv.get("SERVER_PORT")));
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
		System.setProperty("JPA_DDL_AUTO", dotenv.get("JPA_DDL_AUTO"));
		System.setProperty("JPA_SHOW_SQL", dotenv.get("JPA_SHOW_SQL"));
		System.setProperty("JPA_OPEN_IN_VIEW", dotenv.get("JPA_OPEN_IN_VIEW"));

		SpringApplication.run(Application.class, args);
	}

}
