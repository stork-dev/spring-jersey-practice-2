package com.dev.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

@Named
public class BookService {
    @Autowired
    private Validator validator;
    public void validateBook(@Valid Book book) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}