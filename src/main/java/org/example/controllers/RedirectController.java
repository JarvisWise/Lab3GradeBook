package org.example.controllers;

import org.example.dao.implementations.*;
import org.example.entities.*;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.strings.PageName;
import org.example.tools.strings.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;

import static org.example.tools.strings.SessionAttributeName.CURRENT_USER_ID;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {

    private final DAOStudentImpl daoStudent;
    private final DAOTeacherImpl daoTeacher;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;
    private final DAOStudentSubjectImpl daoStudentSubject;

    @Autowired
    public RedirectController(DAOStudentImpl daoStudent, DAOTeacherImpl daoTeacher, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject, DAOStudentSubjectImpl daoStudentSubject) {
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
        this.daoStudentSubject = daoStudentSubject;
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

    @RequestMapping(value = "/profile")
    @GetMapping
    public ModelAndView redirectProfile(@RequestParam("userId") String userId,
                                        @RequestParam("userRole") String userRole,
                                        HttpServletRequest request) {

        //add varial vhen params are empty but session not empty (for MyProfile)
        ModelAndView modelAndView = new ModelAndView();
        //---
        HttpSession session = request.getSession(false);
        String isCurrentProfile =
                session.getAttribute(CURRENT_USER_ID.getSessionAttributeName()).equals(userId)? //do check on null
                        "Yes" : "No";
        //---
        try {
            if (Role.STUDENT.getRole().equals(userRole)) {
                Student user = daoStudent.getStudentById(userId);
                //add check for group or headman is exist
                if(user.getHeadman() != null) {
                    Student userHeadman = daoStudent.getStudentById(user.getHeadman());
                    modelAndView.addObject("userHeadman", userHeadman);
                }
                if(user.getGroupId() != null) {
                    Group userGroup = daoGroup.getGroupById(user.getGroupId());
                    modelAndView.addObject("userGroup", userGroup);
                }
                //add validation on present subject or not
                HashMap<Subject, StudentSubject> studentSubjectMap = daoStudentSubject.getSubjectsInfoByStudentId(userId);
                modelAndView.addObject("studentSubjectMap", studentSubjectMap);

                modelAndView.addObject("user", user);
            } else { //mb one more if
                Teacher user = daoTeacher.getTeacherById(userId);
                //add validation on present subject or not
                List<Subject> teacherSubjectMap = daoSubject.getSubjectsByTeacherId(userId);
                modelAndView.addObject("teacherSubjectMap", teacherSubjectMap);
                modelAndView.addObject("user", user);
            }
        } catch (WrongEntityIdException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("userRole", userRole);
        modelAndView.addObject("isCurrentProfile", isCurrentProfile);
        modelAndView.setViewName(PageName.PROFILE_PAGE.getPageName());
        return modelAndView;
    }

}
