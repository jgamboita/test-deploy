package co.gov.educacionbogota.evaluaciondocenteapi.application.in;

public interface HealthPort <T extends String> extends Port {

        String send(T entity);


}
