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
@RequestMapping(value = "/edit")
public class EditController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;

    @Autowired
    public EditController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
    }

    @RequestMapping(value = "/group/{groupId}")
    @GetMapping
    public ModelAndView editByGroupId(@RequestParam("groupId") String groupId,
                                      @RequestParam("groupName") String groupName) {
        try {
            daoGroup.updateGroup(new Group(groupId, groupName));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/student/{studentId}")
    @GetMapping
    public ModelAndView editByStudentId(@RequestParam("studentId") String studentId,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("headman") String headman,
                                        @RequestParam("password") String password,
                                        @RequestParam("groupId") String groupId) {


        Student student = new Student(
                studentId,
                firstName,
                lastName,
                password,
                headman,
                groupId
        );

        try {
            daoStudent.updateStudent(student);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/subject")
    @GetMapping
    public ModelAndView editBySubjectId(@RequestParam("subjectId") String subjectId,
                                        @RequestParam("subjectName") String subjectName,
                                        @RequestParam("maxGrade") String maxGrade,
                                        @RequestParam("passProcGrade") String passProcGrade) {

        Subject subject = new Subject(
                subjectId,
                subjectName,
                Integer.parseInt(maxGrade),
                Integer.parseInt(passProcGrade)
        );

        try {
            daoSubject.updateSubject(subject);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/task")
    @GetMapping
    public ModelAndView editByTaskId(@RequestParam("taskId") String taskId) {




        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
