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
            if (/*TEACHER.getRole().equals(loginType) && */daoTeacher.isExistTeacherByLogin(loginUserName)) {
                modelAndView = loginTeacher(loginUserName, loginPassword, request);
            } else if (/*STUDENT.getRole().equals(loginType) && */daoStudent.isExistStudentByLogin(loginUserName)) {
                modelAndView = loginStudent(loginUserName, loginPassword, request);
            } else {
                modelAndView.setViewName(ERROR_PAGE.getPageName());
            }
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        return modelAndView;
    }

    private ModelAndView loginTeacher(String loginUserName, String loginPassword, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Teacher teacher = daoTeacher.getTeacherByLoginNameAndPassword(loginUserName, loginPassword);
            setBaseSessionVariables(request, teacher);//
            modelAndView.addObject("username", teacher.getFirstName());
            modelAndView.setViewName(MAIN_PAGE.getPageName());
        } catch (WrongLoginDataException e) {
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        return modelAndView;
    }

    private ModelAndView loginStudent(String loginUserName, String loginPassword, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            Student student = daoStudent.getStudentByLoginNameAndPassword(loginUserName, loginPassword);
            setBaseSessionVariables(request, student);//
            modelAndView.addObject("username", student.getFirstName());
            modelAndView.setViewName(MAIN_PAGE.getPageName());
        } catch (WrongLoginDataException e) {
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        return modelAndView;
    }
}
