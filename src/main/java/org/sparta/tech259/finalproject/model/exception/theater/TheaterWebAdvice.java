package org.sparta.tech259.finalproject.model.exception.theater;

import jakarta.servlet.http.HttpServletRequest;
import org.sparta.tech259.finalproject.controller.web.TheatersWebController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice(assignableTypes = {TheatersWebController.class})
public class TheaterWebAdvice {

    @ExceptionHandler({TheaterIdAlreadyExistsException.class, TheaterIdDoesNotExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String theaterIdExistsException(RuntimeException e,
                                           HttpServletRequest request,
                                           Model model){
        model.addAttribute("ErrorCode", 400);
        model.addAttribute("error", e);
        model.addAttribute("request", request);
        return "theater-error";
    }
}
