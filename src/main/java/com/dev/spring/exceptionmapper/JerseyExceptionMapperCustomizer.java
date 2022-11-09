package com.dev.spring.exceptionmapper;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;
import org.springframework.stereotype.Component;

/**
 * Callback interface that can be implemented by beans wishing to customize Jersey's ResourceConfig before it is used.
 */
@Component
public class JerseyExceptionMapperCustomizer implements ResourceConfigCustomizer {
    @Override
    public void customize(final ResourceConfig config) {

        config.register(GeneralExceptionMapper.class);
        config.register(ConstraintViolationExceptionMapper.class);
        config.register(BadCredentialsExceptionMapper.class);

    }
}