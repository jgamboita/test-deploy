package co.gov.educacionbogota.evaluaciondocenteapi.infra.in;

import co.gov.educacionbogota.evaluaciondocenteapi.application.in.HealthPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/health")
public class HealthAdapter  implements HealthPort<String> {


    @Override
    @GetMapping("/send")
    public String send(@RequestParam() String entity) {

        log.info("Aplicación saludable!!" + entity);
        return "Aplicación saludable!!" + entity;

    }
}
