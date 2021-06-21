package org.example.controllers.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/rest/student")
public class RestStudentController {
    @RequestMapping(value = "/info-by-id")
    @GetMapping
    public ModelAndView getStudentInfoById(@RequestParam("studentId") String studentId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
    @RequestMapping(value = "/info-by-group-id")
    @GetMapping
    public ModelAndView getStudentInfoByGroupId(@RequestParam("groupId") String groupId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
