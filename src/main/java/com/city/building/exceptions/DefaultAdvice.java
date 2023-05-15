package com.city.building.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BuildingNotFoundException.class)
    public ResponseEntity<Object> handleBuildingNotFoundException(BuildingNotFoundException e, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Error", e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);

    }




}
