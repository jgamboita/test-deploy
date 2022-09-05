package co.gov.educacionbogota.evaluaciondocenteapi.infra.in;

import co.gov.educacionbogota.evaluaciondocenteapi.application.in.HealthPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
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
    
    private GroupedOpenApi prepareGroup(String pGroup, String pPath) {
        return GroupedOpenApi.builder()
            .group(pGroup)
            .pathsToMatch(pPath)
            .build();
    }

    @Bean
    public List<GroupedOpenApi> getApis() {
        return List.of(this.prepareGroup("evaluacion-docente-api", "/evaluacion-docente-api/**"));
    }
}
