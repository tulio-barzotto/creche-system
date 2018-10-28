package br.com.crechesystem.crechesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "br.com.crechesystem.crechesystem")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "br.com.crechesystem.crechesystem.repository")
@SpringBootApplication
public class CrecheSystemApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrecheSystemApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Iniciando aplicação Creche System.");
		SpringApplication.run(CrecheSystemApplication.class, args);
	}
}
