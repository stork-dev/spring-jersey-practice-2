package com.dev.spring.config;

import com.dev.spring.GreetingController;
import com.dev.spring.HelloController;
import com.dev.spring.validation.BookResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

/**
 * Entry point for Jersey configuration
 */
@Configuration
@ApplicationPath("/api") // all url paths start with /api/
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloController.class);
        register(GreetingController.class);
        register(BookResource.class);
    }
}
