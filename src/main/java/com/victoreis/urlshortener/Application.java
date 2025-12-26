package com.victoreis.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.victoreis.urlshortener.entity.Url;
import com.victoreis.urlshortener.service.UrlService;

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

	@Bean
	CommandLineRunner commandLineRunner(UrlService urlService) {
		return args -> {
			Url shortUrl = urlService.createShortUrl("https://www.example.com/some/very/long/url");
			System.out.println("SHORTCODE GERADO: " + shortUrl.getShortCode());
		};
	}
}
