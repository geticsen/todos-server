package com.oocl.todos.config;

import com.oocl.todos.exception.ExceptionMsg;
import com.oocl.todos.exception.IllegalOperationException;
import com.oocl.todos.exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchDataException() {
        return ExceptionMsg.NO_SUCH_DATA;
    }

    @ExceptionHandler(IllegalOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleIllegalOperationException() {
        return ExceptionMsg.ILLEGALOPERATION;
    }
}
