package org.example.controllers;

import org.example.dao.DAOInterface;
import org.example.dao.DAOPostgreSQL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    @GetMapping
    public ModelAndView checkUser(@RequestParam("loginType") String loginType,
                                   @RequestParam("loginUserName") String loginUserName,
                                   @RequestParam("loginPassword") String loginPassword) {


        /*if ("teacher".equals(loginType) && DAOPostgreSQL.getInstance().getTeacherByIdAndPassword(loginUserName, loginPassword) != null) {

        } else if ("student".equals(loginType) && DAOPostgreSQL.getInstance().getStudentByIdAndPassword(loginUserName, loginPassword) != null) {

        } else {

        }*/

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
