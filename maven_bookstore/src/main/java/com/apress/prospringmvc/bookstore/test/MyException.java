package com.apress.prospringmvc.bookstore.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Forbidden Error!!!!")
public class MyException extends RuntimeException {
    public MyException() {
        super("Forbidden Error!!!!");
    }
}