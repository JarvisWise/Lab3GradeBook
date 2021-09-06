package org.example.controllers;

import org.example.dao.implementations.DAOGroupImpl;
import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOSubjectImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/delete")
public class DeleteController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;

    @Autowired
    public DeleteController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
    }

    @RequestMapping(value = "/group")
    @GetMapping
    public ModelAndView deleteByGroupId(@RequestParam("groupId") String groupId) {
        daoGroup.deleteGroup(groupId);
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/student")
    @GetMapping
    public ModelAndView deleteByStudentId(@RequestParam("studentId") String studentId) {
        daoStudent.deleteStudent(studentId);
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/subject")
    @GetMapping
    public ModelAndView deleteBySubjectId(@RequestParam("subjectId") String subjectId) {
        daoSubject.deleteSubject(subjectId);
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/task")
    @GetMapping
    public ModelAndView deleteByTaskId(@RequestParam("taskId") String taskId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
