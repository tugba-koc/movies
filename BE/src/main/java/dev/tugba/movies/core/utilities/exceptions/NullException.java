package dev.tugba.movies.core.utilities.exceptions;

public class NullException extends RuntimeException {
    public NullException(String errorCode) {
        super(errorCode);
    }
}