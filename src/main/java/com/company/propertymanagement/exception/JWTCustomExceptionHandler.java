package com.company.propertymanagement.exception;

import com.company.propertymanagement.model.JWTErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Log4j2
public class JWTCustomExceptionHandler {

    @ExceptionHandler(JWTBusinessException.class)
    public ResponseEntity<List<JWTErrorDTO>> handleBussExc(JWTBusinessException be) {
        for (JWTErrorDTO errorDTO : be.getErrors()) {
            log.error("Business exception: {} - {}", errorDTO.getCode(), errorDTO.getMsg());
        }
        return new ResponseEntity<>(be.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
