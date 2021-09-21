package com.creditrepaircloud.parkinglot.exception;


import javax.servlet.http.HttpServletRequest;

import com.creditrepaircloud.parkinglot.domain.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class DataNotFoundExceptionMapper{

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorMessage> dataNotFound(HttpServletRequest req, Exception e)
    {
        ErrorMessage error = new ErrorMessage(e.getMessage(), 404, req.getRequestURI());
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ErrorMessage> dataFound(HttpServletRequest req, Exception e)
    {
        ErrorMessage error = new ErrorMessage(e.getMessage(), 400, req.getRequestURI());
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);

    }

}
