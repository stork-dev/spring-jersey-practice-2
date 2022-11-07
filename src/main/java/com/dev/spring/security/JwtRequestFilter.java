package com.dev.spring.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Inject
    private UserDetailsService userDetailsService;
    @Inject
    private JwtUtil jwtUtil;

    /**
     * extract JWT
     * get username
     * extract UserDetails
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); // get authorization header value

        String username = null;
        String jwt = null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7); // extract JWT
            username = jwtUtil.extractUserName(jwt); // get username
        }
        if(username !=null  && SecurityContextHolder.getContext().getAuthentication() == null) { // something have not gone into security context
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // extract UserDetails
            if(jwtUtil.validateToken(jwt, userDetails)) { // jwt is valid for given user and jwt has not expired
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                // this would have happen by default but i am setting in context only in case of valid jwt for normal flow.
            }
        }

        filterChain.doFilter(request, response);
    }
}
