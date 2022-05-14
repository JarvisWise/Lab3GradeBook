package org.example.controllers;

import org.example.dao.implementations.*;
import org.example.entities.*;
import org.example.tools.strings.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

import static org.example.tools.strings.PageName.*;

@Controller
@RequestMapping(value = "/add")
public class AddController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;
    private final DAOTeacherSubjectImpl daoTeacherSubject;
    private final DAOStudentSubjectImpl daoStudentSubject;

    @Autowired
    public AddController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject, DAOTeacherSubjectImpl daoTeacherSubject, DAOStudentSubjectImpl daoStudentSubject) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
        this.daoTeacherSubject = daoTeacherSubject;
        this.daoStudentSubject = daoStudentSubject;
    }

    @RequestMapping(value = "/group")
    @GetMapping
    public ModelAndView addByGroup(@RequestParam("groupName") String groupName) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            daoGroup.addGroup(new Group(null, groupName));
            modelAndView.setViewName("redirect:/show/group-all");//
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        //add action
        return modelAndView;
        //return "redirect:/path/to/other/controller?param=" + value;
        //TO DO: go to all-group-page
    }

    @RequestMapping(value = "/student")
    @GetMapping
    public ModelAndView addByStudent(@RequestParam("firstName") String firstName,
                                       @RequestParam("loginName") String loginName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("headman") String headman,
                                       @RequestParam("password") String password,
                                       @RequestParam("groupId") String groupId) {

        ModelAndView modelAndView = new ModelAndView();
        Student student = new Student(
                null,
                loginName,
                firstName,
                lastName,
                password,
                Strings.NOT_YET.getStrings().equals(headman) ? null : headman ,
                Strings.NOT_YET.getStrings().equals(groupId) ? null : groupId
        );

        try {
            daoStudent.addStudent(student);
            modelAndView.setViewName("redirect:/show/student-all");//
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        //add action
        return modelAndView;
        //TO DO: go to all-student-page
    }

    @RequestMapping(value = "/subject")
    @GetMapping
    public ModelAndView addBySubject(@RequestParam("subjectName") String subjectName,
                                       @RequestParam("maxGrade") String maxGrade,
                                       @RequestParam("passProcGrade") String passProcGrade) {

        ModelAndView modelAndView = new ModelAndView();
        Subject subject = new Subject(
                null,
                subjectName,
                Integer.parseInt(maxGrade),
                Integer.parseInt(passProcGrade)
        );

        try {
            daoSubject.addSubject(subject);
            modelAndView.setViewName("redirect:/show/subject-all");//
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        //add action
        return modelAndView;
        //TO DO: go to all-subject-page
    }

    // TO DO
    @RequestMapping(value = "/teacher-subject")
    @GetMapping
    public ModelAndView addTeacherSubject(@RequestParam("teacherId") String teacherId,
                                     @RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        TeacherSubject teacherSubject = new TeacherSubject(
          subjectId,
          teacherId
        );

        try {
            daoTeacherSubject.addTeacherSubject(teacherSubject);
            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }

    // TO DO
    @RequestMapping(value = "/student-subject")
    @GetMapping
    public ModelAndView addStudentSubject(@RequestParam("studentId") String studentId,
                                          @RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        StudentSubject studentSubject = new StudentSubject(
                null,
                studentId,
                subjectId,
                "1", //for remove
                0
        );

        try {
            daoStudentSubject.addStudentSubject(studentSubject);
            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
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
