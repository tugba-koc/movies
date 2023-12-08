package dev.tugba.movies.core.utilities.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewNotFoundException(ReviewNotFoundException exception, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());

        List<String> errorMessage = Arrays.asList(exception.getMessage());

        ErrorInfos errorInfos = new ErrorInfos();
        errorInfos.setCodeError(errorMessage);

        errorObject.setErrorInfos(errorInfos);
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorObject> handleMovieNotFoundException(MovieNotFoundException exception, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());

        List<String> errorMessage = Arrays.asList(exception.getMessage());

        ErrorInfos errorInfos = new ErrorInfos();
        errorInfos.setCodeError(errorMessage);

        errorObject.setErrorInfos(errorInfos);
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(NullException.class)
    public ResponseEntity<ErrorObject> handleNullException(NullException exception, WebRequest request) {

        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());

        List<String> errorMessage = Arrays.asList(exception.getMessage());

        ErrorInfos errorInfos = new ErrorInfos();
        errorInfos.setCodeError(errorMessage);

        errorObject.setErrorInfos(errorInfos);
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
}
