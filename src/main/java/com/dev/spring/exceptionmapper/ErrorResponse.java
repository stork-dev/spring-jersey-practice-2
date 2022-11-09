package com.dev.spring.exceptionmapper;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class ErrorResponse {

    private String error;
    private String path;
    private String timestamp;
    private int status;
}
