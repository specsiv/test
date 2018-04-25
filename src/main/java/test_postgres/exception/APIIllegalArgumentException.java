package test_postgres.exception;

public class APIIllegalArgumentException extends APIException {
    public APIIllegalArgumentException(String message) {
        super(message);
    }
}