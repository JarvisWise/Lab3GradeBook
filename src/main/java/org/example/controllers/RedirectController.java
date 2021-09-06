package org.example.controllers;

import org.example.dao.implementations.DAOGroupImpl;
import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOSubjectImpl;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;

    @Autowired
    public RedirectController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
    }

    @RequestMapping(value = "/add/student")
    @GetMapping
    public ModelAndView redirectAddStudent() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("action", "add");
        modelAndView.setViewName("add-edit-student-page");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/student")
    @GetMapping
    public ModelAndView redirectEditStudent(@RequestParam("studentId") String studentId) {

        ModelAndView modelAndView = new ModelAndView();
        Student student = null;
        try {
            student = daoStudent.getStudentById(studentId);
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("student", student);
        modelAndView.addObject("action", "edit");
        modelAndView.setViewName("add-edit-student-page");
        return modelAndView;
    }

    @RequestMapping(value = "/add/group")
    @GetMapping
    public ModelAndView redirectAddGroup() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("action", "add");
        modelAndView.setViewName("add-edit-group-page");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/group")
    @GetMapping
    public ModelAndView redirectEditGroup(@RequestParam("groupId") String groupId) {

        ModelAndView modelAndView = new ModelAndView();
        Group group = null;
        try {
            group = daoGroup.getGroupById(groupId);
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("student", group);
        modelAndView.addObject("action", "add");
        modelAndView.setViewName("add-edit-group-page");
        return modelAndView;
    }

    @RequestMapping(value = "/add/subject")
    @GetMapping
    public ModelAndView redirectAddSubject() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("action", "add");
        modelAndView.setViewName("add-edit-subject-page");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/subject")
    @GetMapping
    public ModelAndView redirectEditSubject(@RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        Subject subject = null;
        try {
            subject = daoSubject.getSubjectById(subjectId);
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("subject", subject);
        modelAndView.addObject("action", "add");
        modelAndView.setViewName("add-edit-group-page");
        return modelAndView;
    }

    @RequestMapping(value = "/main")
    @GetMapping
    public ModelAndView redirectMain() {
        return new ModelAndView("main-page");
    }

}
