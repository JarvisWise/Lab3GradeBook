package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class LogoutController {

    @RequestMapping(value = "/login")
    @GetMapping
    public ModelAndView logout() {
        return new ModelAndView("error-page");
    }
}
