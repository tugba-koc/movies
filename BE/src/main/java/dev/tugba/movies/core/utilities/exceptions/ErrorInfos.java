package dev.tugba.movies.core.utilities.exceptions;

import java.util.List;

import lombok.Data;

@Data
public class ErrorInfos {
    private List<String> codeError;
}

