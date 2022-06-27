package SpecialException;

public class ServerDownException extends RuntimeException{
    public ServerDownException() {
    }

    public ServerDownException(String message) {
        super(message);
    }
}
