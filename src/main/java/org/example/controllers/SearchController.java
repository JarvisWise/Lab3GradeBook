package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(value = "/by-group-id")
    @GetMapping
    public ModelAndView searchByGroupId(@RequestParam("groupId") String groupId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }

    @RequestMapping(value = "/by-student-id")
    @GetMapping
    public ModelAndView searchByStudentId(@RequestParam("studentId") String studentId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }

    @RequestMapping(value = "/by-subject-id")
    @GetMapping
    public ModelAndView searchBySubjectId(@RequestParam("subjectId") String subjectId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }

}
