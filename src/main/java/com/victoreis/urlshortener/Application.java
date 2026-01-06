package com.victoreis.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// Tenta carregar o .env, mas ignora se não existir (ex: ambiente Docker)
		Dotenv dotenv = Dotenv.configure()
			.ignoreIfMissing()
			.load();
		
		// Só define as propriedades se estiverem no .env e não já definidas no sistema
		setPropertyIfPresent("SERVER_PORT", dotenv);
		setPropertyIfPresent("DB_URL", dotenv);
		setPropertyIfPresent("DB_USERNAME", dotenv);
		setPropertyIfPresent("DB_PASSWORD", dotenv);
		setPropertyIfPresent("DB_DRIVER", dotenv);
		setPropertyIfPresent("JPA_DDL_AUTO", dotenv);
		setPropertyIfPresent("JPA_SHOW_SQL", dotenv);
		setPropertyIfPresent("JPA_OPEN_IN_VIEW", dotenv);

		SpringApplication.run(Application.class, args);
	}

	private static void setPropertyIfPresent(String key, Dotenv dotenv) {
		// Prioriza variáveis de ambiente do sistema, depois .env
		String value = System.getenv(key);
		if (value == null) {
			value = dotenv.get(key);
		}
		if (value != null) {
			System.setProperty(key, value);
		}
	}
}
