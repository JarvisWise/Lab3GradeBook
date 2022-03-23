package org.example.controllers;

import org.example.dao.implementations.DAOGroupImpl;
import org.example.dao.implementations.DAOStudentImpl;
import org.example.dao.implementations.DAOStudentSubjectImpl;
import org.example.dao.implementations.DAOSubjectImpl;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.StudentSubject;
import org.example.entities.Subject;
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
import java.util.List;

import static org.example.tools.strings.PageName.*;
import static org.example.tools.strings.SessionAttributeName.CURRENT_USER_ROLE;

@Controller
@RequestMapping(value = "/show")
public class ShowController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;
    private final DAOStudentSubjectImpl daoStudentSubject;

    @Autowired
    public ShowController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject, DAOStudentSubjectImpl daoStudentSubject) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
        this.daoStudentSubject = daoStudentSubject;
    }

    @RequestMapping(value = "/group")
    @GetMapping
    public ModelAndView showByGroupId(@RequestParam("groupId") String groupId) {

        ModelAndView modelAndView = new ModelAndView();
        Group group = null;
        List<Student> studentList = null;
        try {
            group = daoGroup.getGroupById(groupId);
            studentList = daoStudent.getStudentsByGroupId(groupId);
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("group", group);
        modelAndView.addObject("studentList", studentList);
        modelAndView.setViewName("show-group-page");
        return modelAndView;
    }

    @RequestMapping(value = "/student")
    @GetMapping
    public ModelAndView showByStudentId(@RequestParam("studentId") String studentId) {

        ModelAndView modelAndView = new ModelAndView();
        Student student = null;
        Student headman = null;
        Group group = null;
        List<StudentSubject> studentSubjectList = null;
        try {
            student = daoStudent.getStudentById(studentId);
            headman = daoStudent.getStudentById(student.getHeadman());
            group = daoGroup.getGroupById(student.getGroupId());
            studentSubjectList = daoStudentSubject.getStudentSubjectsByStudentId(studentId);
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("student", student);
        modelAndView.addObject("headman", headman);
        modelAndView.addObject("group", group);
        modelAndView.addObject("studentSubjectList", studentSubjectList);
        modelAndView.setViewName("show-student-page");
        return modelAndView;
    }

    @RequestMapping(value = "/subject")
    @GetMapping
    public ModelAndView showBySubjectId(@RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "WHYYYY!");
        modelAndView.setViewName("main-page");
        return modelAndView;
    }

    //--------------show all
    @RequestMapping(value = "/student-all")
    @GetMapping
    public ModelAndView showAllStudent(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Student> studentAllList = daoStudent.getAllStudents();

            //--time solution
            HttpSession session = request.getSession(false);
            String currentRole = (String)session.getAttribute(CURRENT_USER_ROLE.getSessionAttributeName());
            modelAndView.addObject("userType", currentRole);
            //--time solution
            //--and mb remove from [studentAllList] list current user

            modelAndView.addObject("students", studentAllList);
            modelAndView.setViewName(STUDENT_LIST_PAGE.getPageName());
        } catch (WrongEntityIdException e) {
            //add to error_page params
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/group-all")
    @GetMapping
    public ModelAndView showAllGroup(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Group> groupAllList = daoGroup.getAllGroups();

            //--time solution
            HttpSession session = request.getSession(false);
            String currentRole = (String)session.getAttribute(CURRENT_USER_ROLE.getSessionAttributeName());
            modelAndView.addObject("userType", currentRole);
            //--time solution

            modelAndView.addObject("groups", groupAllList);
            modelAndView.setViewName(GROUP_LIST_PAGE.getPageName());
        } catch (WrongEntityIdException e) {
            //add to error_page params
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/subject-all")
    @GetMapping
    public ModelAndView showAllSubject(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Subject> subjectAllList = daoSubject.getAllSubjects();

            //--time solution
            HttpSession session = request.getSession(false);
            String currentRole = (String)session.getAttribute(CURRENT_USER_ROLE.getSessionAttributeName());
            modelAndView.addObject("userType", currentRole);
            //--time solution

            modelAndView.addObject("subjects", subjectAllList);
            modelAndView.setViewName(SUBJECT_LIST_PAGE.getPageName());
        } catch (WrongEntityIdException e) {
            //add to error_page params
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }
        return modelAndView;
    }
}
