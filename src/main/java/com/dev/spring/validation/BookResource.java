package com.dev.spring.validation;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.groups.ConvertGroup;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/books")
public class BookResource {

    @Inject
    Validator validator;

    @Path("/manual-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result tryMeManualValidation(Book book) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (violations.isEmpty()) {
            return new Result("Book is valid! It was validated by manual validation.");
        } else {
            throw new ConstraintViolationException(violations);
        }
    }

    @Path("/end-point-method-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result tryMeEndPointMethodValidation(@Valid Book book) {
        return new Result("Book is valid! It was validated by end point method validation.");
    }

    @Inject BookService bookService;

    @Path("/service-method-validation")
    @POST
    public Result tryMeServiceMethodValidation(Book book) {
        bookService.validateBook(book);
        return new Result("Book is valid! It was validated by service method validation.");
    }

    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(@Valid @ConvertGroup(to = ValidationGroups.Post.class) Book book) {
        // ...
    }

    @Path("/put")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(@Valid @ConvertGroup(to = ValidationGroups.Put.class) Book book) {
        // ...
    }
}