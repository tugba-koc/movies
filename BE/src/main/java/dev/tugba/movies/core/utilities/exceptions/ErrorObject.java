package dev.tugba.movies.core.utilities.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private ErrorInfos errorInfos;
    private Integer statusCode;
    private Date timestamp;
}
