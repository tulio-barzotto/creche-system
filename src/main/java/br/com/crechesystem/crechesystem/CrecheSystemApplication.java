package br.com.crechesystem.crechesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrecheSystemApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrecheSystemApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Iniciando aplicação Creche System.");
		SpringApplication.run(CrecheSystemApplication.class, args);
	}
}
