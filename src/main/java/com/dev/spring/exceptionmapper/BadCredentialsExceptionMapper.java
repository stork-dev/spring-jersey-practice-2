package com.dev.spring.exceptionmapper;

import org.springframework.security.authentication.BadCredentialsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Collections;


@Provider
public class BadCredentialsExceptionMapper extends AbstractExceptionMapper<BadCredentialsException> {

    @Override
    public Response toResponse(BadCredentialsException e) {
        return toResponse(Collections.singletonList(e.getMessage()),
                401); // 401 Unauthorized
    }
}
