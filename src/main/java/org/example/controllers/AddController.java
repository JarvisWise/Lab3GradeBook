package org.example.controllers;

import org.example.dao.implementations.DAOGroupImpl;
import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOSubjectImpl;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value = "/add")
public class AddController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;

    @Autowired
    public AddController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
    }

    @RequestMapping(value = "/group")
    @GetMapping
    public ModelAndView addByGroupId(@RequestParam("groupName") String groupName) {
        try {
            daoGroup.addGroup(new Group("new", groupName));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/student")
    @GetMapping
    public ModelAndView addByStudentId(@RequestParam("firstName") String firstName,
                                       @RequestParam("loginName") String loginName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("headman") String headman,
                                       @RequestParam("password") String password,
                                       @RequestParam("groupId") String groupId) {

        Student student = new Student(
                "new",
                loginName,
                firstName,
                lastName,
                password,
                headman,
                groupId
        );

        try {
            daoStudent.addStudent(student);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/subject")
    @GetMapping
    public ModelAndView addBySubjectId(@RequestParam("subjectName") String subjectName,
                                       @RequestParam("maxGrade") String maxGrade,
                                       @RequestParam("passProcGrade") String passProcGrade) {

        Subject subject = new Subject(
                "new",
                subjectName,
                Integer.parseInt(maxGrade),
                Integer.parseInt(passProcGrade)
        );

        try {
            daoSubject.addSubject(subject);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/task")
    @GetMapping
    public ModelAndView addByTaskId(@RequestParam("taskId") String taskId) {

        ///
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
