package org.sparta.tech259.finalproject.model.exception.theater;

import jakarta.servlet.http.HttpServletRequest;
import org.sparta.tech259.finalproject.controller.rest.TheatersRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {TheatersRestController.class})
public class TheaterAdvice {

    @ExceptionHandler({TheaterIdAlreadyExistsException.class, TheaterIdDoesNotExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<TheaterResponse> theaterIdExistsException(TheaterIdAlreadyExistsException e, HttpServletRequest request){
        TheaterResponse response = new TheaterResponse(e.getMessage(),400,request.getRequestURI());
        return ResponseEntity.status(400).body(response);
    }
}
