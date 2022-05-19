package org.example.controllers;

import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOTeacherImpl;
import org.example.entities.Student;
import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static org.example.tools.strings.PageName.*;
import static org.example.tools.strings.Role.*;

@Controller
public class LoginController extends AbstractController{

    private final DAOStudentImpl daoStudent;
    private final DAOTeacherImpl daoTeacher;

    @Autowired
    public LoginController(DAOStudentImpl daoStudent, DAOTeacherImpl daoTeacher) {
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
    }

    @RequestMapping(value = "/login")
    @GetMapping
    public ModelAndView checkUser(@RequestParam("loginUserName") String loginUserName,
                                   @RequestParam("loginPassword") String loginPassword,
                                   HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            if (daoTeacher.isExistTeacherByLogin(loginUserName)) {
                modelAndView = loginTeacher(loginUserName, loginPassword, request);
            } else if (daoStudent.isExistStudentByLogin(loginUserName)) {
                modelAndView = loginStudent(loginUserName, loginPassword, request);
            } else {
                modelAndView.addObject("ExceptionMessage", "You entered wrong login!");
                modelAndView.setViewName(LOGIN_PAGE.getPageName());
            }
        } catch (SQLException e) {
            modelAndView.addObject("ExceptionMessage", "Sorry, we have unexpected error!");
            modelAndView.setViewName(LOGIN_PAGE.getPageName());
        }
        return modelAndView;
    }

    private ModelAndView loginTeacher(String loginUserName, String loginPassword, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Teacher teacher = daoTeacher.getTeacherByLoginNameAndPassword(loginUserName, loginPassword);
            setBaseSessionVariables(request, teacher);
            modelAndView.setViewName("redirect:/show/teacher?teacherId=" + teacher.getTeacherId());
        } catch (WrongLoginDataException e) {
            modelAndView.addObject("ExceptionMessage", "You entered wrong password!");
            modelAndView.setViewName(LOGIN_PAGE.getPageName());
        }
        return modelAndView;
    }

    private ModelAndView loginStudent(String loginUserName, String loginPassword, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Student student = daoStudent.getStudentByLoginNameAndPassword(loginUserName, loginPassword);
            setBaseSessionVariables(request, student);
            modelAndView.setViewName("redirect:/show/student?studentId=" + student.getStudentId());
        } catch (WrongLoginDataException e) {
            modelAndView.addObject("ExceptionMessage", "You entered wrong password!");
            modelAndView.setViewName(LOGIN_PAGE.getPageName());
        }
        return modelAndView;
    }
}
