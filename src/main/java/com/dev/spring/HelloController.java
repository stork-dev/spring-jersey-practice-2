package com.dev.spring;


import com.dev.spring.security.AuthenticationRequest;
import com.dev.spring.security.AuthenticationResponse;
import com.dev.spring.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class HelloController {

	@Inject
	private AuthenticationManager authenticationManager;
	@Inject
	private UserDetailsService userDetailsService;
	@Inject
	private JwtUtil jwtUtil;

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

	@Path("/authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public AuthenticationResponse createAuthenticationToken(@Valid @NotNull(message = "No Payload") AuthenticationRequest request) throws Exception {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword()
		)); // can throw runtime BadCredentialsException

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		String jwt = jwtUtil.generateToken(userDetails);
		return new AuthenticationResponse(jwt);
	}

}