package it.infobasic.gestioneutenti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GestioneutentiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneutentiApplication.class, args);
	}

}
