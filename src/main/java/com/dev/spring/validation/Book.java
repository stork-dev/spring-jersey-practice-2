package com.dev.spring.validation;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class Book {
    @Null(groups = ValidationGroups.Post.class, message="id is not required.")
    @NotNull(groups = ValidationGroups.Put.class, message="id is required.")
    public Long id;

    @NotBlank(message="title may not be blank.")
    public String title;

    @NotBlank(message="author may not be blank.")
    public String author;

    @Min(message="Author has been very lazy.", value=1)
    public double pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPages() {
        return pages;
    }

    public void setPages(double pages) {
        this.pages = pages;
    }
}