package com.dev.spring.exceptionmapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.stream.Collectors;


@Provider
public class ConstraintViolationExceptionMapper extends AbstractExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return toResponse(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()),
                422); // 422 Unprocessable Entity
    }
}
