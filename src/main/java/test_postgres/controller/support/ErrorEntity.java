package test_postgres.controller.support;

import org.springframework.http.HttpStatus;
import test_postgres.exception.APIException;

public class ErrorEntity {
    private String message;
    private String error;
    private int status;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public ErrorEntity(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public ErrorEntity(APIException e, HttpStatus httpStatus){
        message = e.getMessage();
        error = httpStatus.getReasonPhrase();
        status = httpStatus.value();
    }
}