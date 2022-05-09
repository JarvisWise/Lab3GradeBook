package org.example.controllers;

import org.example.dao.implementations.DAOGroupImpl;
import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOSubjectImpl;
import org.example.dao.implementations.DAOTeacherImpl;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.example.entities.User;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.strings.PageName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static org.example.tools.strings.Role.STUDENT;
import static org.example.tools.strings.Role.TEACHER;
import static org.example.tools.strings.SessionAttributeName.*;

@Controller
@RequestMapping(value = "/edit")
public class EditController {

    private final DAOStudentImpl daoStudent;
    private final DAOTeacherImpl daoTeacher;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;

    @Autowired
    public EditController(DAOStudentImpl daoStudent, DAOTeacherImpl daoTeacher, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject) {
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
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
                                        @RequestParam("loginName") String loginName,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName,
                                        @RequestParam("headman") String headman,
                                        @RequestParam("password") String password,
                                        @RequestParam("groupId") String groupId) {

        Student student = new Student(
                studentId, loginName, firstName,
                lastName, password, headman, groupId
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

    ///------------------
    //-- try to modify
    @RequestMapping(value = "/user-password")
    @GetMapping
    public ModelAndView changeUserPassword(@RequestParam("newPassword") String newPassword,
                                           @RequestParam("confirmPassword") String confirmPassword,
                                           HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        //add all validation
        if(newPassword.equals(confirmPassword)) {
            HttpSession session = request.getSession(false);
            String currentUserId = (String) session.getAttribute(CURRENT_USER_ID.getSessionAttributeName());
            String currentUserRole = (String) session.getAttribute(CURRENT_USER_ROLE.getSessionAttributeName());

            User user = null;
            try {
                if (STUDENT.getRole().equals(currentUserRole)) {
                    daoStudent.changeStudentPassword(currentUserId, newPassword);
                    user = daoStudent.getStudentById(currentUserId);
                } else if (TEACHER.getRole().equals(currentUserRole)) {
                    daoTeacher.changeTeacherPassword(currentUserId, newPassword);
                    user = daoTeacher.getTeacherById(currentUserId);
                } else {
                    //some validation
                }
            } catch (SQLException | WrongEntityIdException throwables) {
                throwables.printStackTrace();
            }
            modelAndView.addObject("user", user);
            modelAndView.addObject("userRole", currentUserRole);
        }

        modelAndView.addObject("isCurrentProfile", "Yes");
        modelAndView.setViewName(PageName.PROFILE_PAGE.getPageName());
        return modelAndView;
    }
}
