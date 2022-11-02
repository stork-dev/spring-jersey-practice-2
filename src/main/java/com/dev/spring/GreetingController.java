package com.dev.spring;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("")
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Path("/greeting")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Greeting greeting(@DefaultValue("world") @QueryParam("name") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}