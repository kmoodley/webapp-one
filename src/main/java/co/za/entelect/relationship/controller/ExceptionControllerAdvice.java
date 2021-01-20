package co.za.entelect.relationship.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice
{
    @ExceptionHandler
    public String exception(Exception exception, Model model)
    {
        exception.printStackTrace();
        model.addAttribute("exception",exception);
        return "exception";
    }
}
