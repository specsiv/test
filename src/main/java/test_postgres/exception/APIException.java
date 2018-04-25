package test_postgres.exception;

public class APIException extends RuntimeException {
    public APIException(String message){
        super(message);
    }

    public APIException(String message, Throwable cause){
        super(message, cause);
    }
}