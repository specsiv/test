package test_postgres.controller.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import test_postgres.exception.APIIllegalArgumentException;
import test_postgres.exception.APINotFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "test_postgres.controller")
public class APIExceptionHandlerControllerAdvice {
    private final ObjectMapper objectMapper;

    @Autowired
    public APIExceptionHandlerControllerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<String> defaultExceptionHandler(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();

        //обрежем очень длинный стектрейс
        if (sw.getBuffer().length() > 65536) {
//            sw.flush(); ???Зачем???
            sw.getBuffer().delete(65524, sw.getBuffer().length());
            sw.append("<truncated>"); // 11 символов
            sw.flush();
        }

        Map<String, Object> er = new HashMap<>();
        er.put("code", 500);
        er.put("message", throwable.getMessage());
        er.put("dateTime", ZonedDateTime.now(ZoneOffset.UTC));
        er.put("description", sw.toString());

        return buildResponse(throwable, HttpStatus.INTERNAL_SERVER_ERROR, er);
    }

    @ExceptionHandler(APIIllegalArgumentException.class)
    @ResponseBody
    public ErrorEntity handleAPIIllegalArgumentException(APIIllegalArgumentException e){
        return new ErrorEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(APINotFoundException.class)
    @ResponseBody
    public ErrorEntity handleAPINotFoundException(APINotFoundException e){
        return new ErrorEntity(e, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> buildResponse(Throwable t, HttpStatus httpStatus, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(body), headers, httpStatus);
        } catch (JsonProcessingException e) {
            String message = String.format("Ошибка обработки ошибки REST API\nОшибка: %s\nОшибка REST API: %s",
                    e.getMessage(), t.getMessage());

            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}