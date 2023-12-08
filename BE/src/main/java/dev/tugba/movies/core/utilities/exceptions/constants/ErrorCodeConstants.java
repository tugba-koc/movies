package dev.tugba.movies.core.utilities.exceptions.constants;

public enum ErrorCodeConstants {
    REVIEW_NOT_FOUND("1092"),
    MOVIE_NOT_FOUND("1093"),
    NULL_EXCEPTION("1094");

    private final String errorCode;


    ErrorCodeConstants(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}



