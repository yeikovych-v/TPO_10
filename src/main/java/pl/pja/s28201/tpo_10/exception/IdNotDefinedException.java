package pl.pja.s28201.tpo_10.exception;

public class IdNotDefinedException extends RuntimeException {
    public IdNotDefinedException(String message) {
        super(message);
    }

    public IdNotDefinedException() {
    }
}
