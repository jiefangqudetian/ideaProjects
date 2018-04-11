package com.kaishengit.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IOException.class)
    public String ioExceptionHandler(){
        return "error/500";
    }
}
