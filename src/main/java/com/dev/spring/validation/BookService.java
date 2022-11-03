package com.dev.spring.validation;

import javax.inject.Named;
import javax.validation.Valid;

@Named
public class BookService {

    public void validateBook(@Valid Book book) {
        // your business logic here
    }
}