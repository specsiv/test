package test_postgres.exception;

public class APINotFoundException extends APIException {
    public APINotFoundException(String message) {
        super(message);
    }
}