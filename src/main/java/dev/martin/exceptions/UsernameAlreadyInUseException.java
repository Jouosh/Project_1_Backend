package dev.martin.exceptions;

public class UsernameAlreadyInUseException extends RuntimeException{

    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
