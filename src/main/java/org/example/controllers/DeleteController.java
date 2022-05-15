package org.example.controllers;

import org.example.dao.implementations.*;
import org.example.entities.TeacherSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.example.tools.strings.PageName.ERROR_PAGE;

@Controller
@RequestMapping(value = "/delete")
public class DeleteController {

    private final DAOStudentImpl daoStudent;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;
    private final DAOTeacherSubjectImpl daoTeacherSubject;
    private final DAOStudentSubjectImpl daoStudentSubject;
    private final DAOTaskImpl daoTask;

    @Autowired
    public DeleteController(DAOStudentImpl daoStudent, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject, DAOTeacherSubjectImpl daoTeacherSubject, DAOStudentSubjectImpl daoStudentSubject, DAOTaskImpl daoTask) {
        this.daoStudent = daoStudent;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
        this.daoTeacherSubject = daoTeacherSubject;
        this.daoStudentSubject = daoStudentSubject;
        this.daoTask = daoTask;
    }

    @RequestMapping(value = "/group")
    @GetMapping
    public ModelAndView deleteByGroupId(@RequestParam("groupId") String groupId) {
        daoGroup.deleteGroup(groupId);
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/student")
    @GetMapping
    public ModelAndView deleteByStudentId(@RequestParam("studentId") String studentId) {
        daoStudent.deleteStudent(studentId);
        //add action
        return new ModelAndView("main-page");
    }

    @RequestMapping(value = "/subject")
    @GetMapping
    public ModelAndView deleteBySubjectId(@RequestParam("subjectId") String subjectId) {
        daoSubject.deleteSubject(subjectId);
        //add action
        return new ModelAndView("main-page");
    }

    //TO DO
    @RequestMapping(value = "/teacher-subject")
    @GetMapping
    public ModelAndView deleteTeacherSubjectById(@RequestParam("teacherId") String teacherId,
                                                 @RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        TeacherSubject teacherSubject = new TeacherSubject(
                subjectId,
                teacherId
        );

        try {
            daoTeacherSubject.deleteTeacherSubject(teacherSubject);
            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (Exception throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }

    //TO DO
    @RequestMapping(value = "/student-subject")
    @GetMapping
    public ModelAndView deleteStudentSubjectById(@RequestParam("studentId") String studentId,
                                                 @RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            daoStudentSubject.deleteStudentSubject(studentId, subjectId);
            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (Exception throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }

    @RequestMapping(value = "/task")
    @GetMapping
    public ModelAndView deleteByTaskId(@RequestParam("taskId") String taskId,
                                       @RequestParam("subjectId") String subjectId) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            daoTask.deleteTask(taskId);
            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (Exception throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }
}
