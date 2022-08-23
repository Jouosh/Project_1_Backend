package dev.martin.exceptions;

public class NoTownspersonFoundException extends RuntimeException{

    public NoTownspersonFoundException(String message) {
        super(message);
    }
}
