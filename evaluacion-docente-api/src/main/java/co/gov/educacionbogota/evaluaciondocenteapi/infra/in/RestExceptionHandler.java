package co.gov.educacionbogota.evaluaciondocenteapi.infra.in;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    // 400

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapErrors.put(fieldName, errorMessage);
        });
        errors.add(mapErrors);
        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return handleExceptionInternal(ex, customApiError, headers, customApiError.getError(), request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapErrors.put(fieldName, errorMessage);
        });
        errors.add(mapErrors);
        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return handleExceptionInternal(ex, customApiError, headers, customApiError.getError(), request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getPropertyName();
        String errorMessage = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);


        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getError());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getRequestPartName();
        String errorMessage = ex.getRequestPartName() + " part is missing";
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);
        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getError());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getParameterName();
        String errorMessage = ex.getParameterName() + " part is missing";
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);
        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getError());
    }

    //

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getName();
        String errorMessage = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);

        final CustomApiError customApiError = new CustomApiError(new Date(), 400, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getError());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        ex.getConstraintViolations().forEach(error -> {
            String fieldName = ((ConstraintViolation<?>) error).getRootBeanClass().getName();
            String errorMessage = error.getRootBeanClass().getName() + " " + error.getPropertyPath() + ": " + error.getMessage();
            mapErrors.put(fieldName, errorMessage);
        });
        errors.add(mapErrors);

        final CustomApiError customApiError = new CustomApiError(new Date(), 400, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getError());
    }

    // 404

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getHttpMethod() + " " + ex.getRequestURL();
        String errorMessage = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);

        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getStatus());
    }

    // 405

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());


        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getMethod();
        String errorMessage = builder.toString();
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);

        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getStatus());
    }

    // 415

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = ex.getContentType().toString();
        String errorMessage = builder.substring(0, builder.length() - 2);
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);

        final CustomApiError customApiError = new CustomApiError(new Date(), status.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getStatus());
    }

    // 500

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        final List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapErrors = new HashMap<String, Object>();
        String fieldName = "general error";
        String errorMessage = ex.getMessage();
        mapErrors.put(fieldName, errorMessage);
        errors.add(mapErrors);
        final CustomApiError customApiError = new CustomApiError(new Date(), 500, HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), request.getContextPath(), errors);
        return new ResponseEntity<Object>(customApiError, new HttpHeaders(), customApiError.getStatus());
    }


}
