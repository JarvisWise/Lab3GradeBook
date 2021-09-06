package org.example.controllers;

import org.example.dao.connection.DAOPostgreSQL;
import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOTeacherImpl;
import org.example.entities.Student;
import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongLoginDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final DAOStudentImpl daoStudent;
    private final DAOTeacherImpl daoTeacher;

    @Autowired
    public LoginController(DAOStudentImpl daoStudent, DAOTeacherImpl daoTeacher) {
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
    }

    @RequestMapping(value = "/login")
    @GetMapping
    public ModelAndView checkUser(@RequestParam("loginType") String loginType,
                                   @RequestParam("loginUserName") String loginUserName,
                                   @RequestParam("loginPassword") String loginPassword) {

        ModelAndView modelAndView = new ModelAndView();
        if ("teacher".equals(loginType)) {
            try {
                Teacher teacher = daoTeacher.getTeacherByIdAndPassword(loginUserName, loginPassword);
                modelAndView.addObject("username", teacher.getFirstName());
                modelAndView.setViewName("main-page");
            } catch (WrongLoginDataException e) {
                modelAndView.setViewName("error-page");
            }

        } else  if ("student".equals(loginType)) {
            try {
                Student student = daoStudent.getStudentByIdAndPassword(loginUserName, loginPassword);
                modelAndView.addObject("username", student.getFirstName());
                modelAndView.setViewName("main-page");
            } catch (WrongLoginDataException e) {
                modelAndView.setViewName("error-page");
            }
        }
        return modelAndView;
    }
}
