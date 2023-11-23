package co.gov.educacionbogota.evaluaciondocenteapi.infra.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Getter
public class CustomApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD hh:mm:ssZ")

    private Date timestamp;
    private int status;
    private HttpStatus error;
    private String message;
    private String path;
    private List<Map<String, Object>> others;

    //

    public CustomApiError() {
        super();
    }

    public CustomApiError(Date timestamp, int status, final HttpStatus error, final String message,String path, final List<Map<String, Object>> others) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.others = others;
    }





    public final List<Map<String, Object>> getOthers() {
        return others;
    }

    public void setOthers(final List<Map<String, Object>> errors) {
        this.others = errors;
    }
}
