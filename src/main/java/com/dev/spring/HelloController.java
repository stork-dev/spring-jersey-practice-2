package com.dev.spring;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloController {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GET
	@Path("hello2")
	@Produces(MediaType.TEXT_PLAIN)
	public String index2() {
		return "Hello2: Greetings from Spring Boot!";
	}

}