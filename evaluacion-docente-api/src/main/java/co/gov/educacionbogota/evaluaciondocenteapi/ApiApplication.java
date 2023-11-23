package co.gov.educacionbogota.evaluaciondocenteapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.gov.educacionbogota.evaluaciondocenteapi.infra.in.HealthAdapter;


@SpringBootApplication
@Slf4j
public class ApiApplication {

	public static void main(String[] args) {
		log.info("Aplicación saludable e iniciada!!" );

		SpringApplication.run(ApiApplication.class, args);
	}

}
