package co.gov.educacionbogota.evaluaciondocenteapi.infra.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
class CommandLineRunnerAdapter implements CommandLineRunner {

    private final Environment environment;

    public void run(String... args) throws Exception {
        try {
            log.info("STARTING THE APPLICATION");
            log.info("received Variable ZIPKIN_SENDER_TYPE: " + environment.getProperty("ZIPKIN_SENDER_TYPE"));

            log.info("APPLICATION FINISHED");
        } catch (Exception e) {
            log.error("APPLICATION FINISHED with errors " + e.getMessage());



        }
    }

}
