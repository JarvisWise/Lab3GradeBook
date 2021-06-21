package org.example.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/rest/teacher")
public class RestTeacherController {

    @RequestMapping(value = "/info-by-id")
    @GetMapping
    public ModelAndView getStudentInfoById(@RequestParam("teacherId") String teacherId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
