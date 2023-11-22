package com.company.propertymanagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bexc) {
        System.out.println("Business exception is thrown");
        return new ResponseEntity<List<ErrorModel>>(bexc.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
