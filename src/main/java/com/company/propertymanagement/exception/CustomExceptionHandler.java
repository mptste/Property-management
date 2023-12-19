package com.company.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorList = manv.getFieldErrors();

        for (FieldError fe : fieldErrorList) {
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bexc) {
        for (ErrorModel em : bexc.getErrors()) {
            logger.debug("BusinessException has occurred - level-debug: {} - {}", em.getCode(), em.getMessage());
            logger.info("BusinessException has occurred - level-info: {} - {}", em.getCode(), em.getMessage());
            logger.warn("BusinessException has occurred - level-warn: {} - {}", em.getCode(), em.getMessage());
            logger.error("BusinessException has occurred - level-error: {} - {}", em.getCode(), em.getMessage());
        }
        return new ResponseEntity<>(bexc.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
