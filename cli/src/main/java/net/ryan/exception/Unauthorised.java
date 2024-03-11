package net.ryan.exception;

public class Unauthorised extends RuntimeException {

    public Unauthorised(String message) {
        super(message);
    }
}
