package org.example.controllers;

import org.example.dao.implementations.*;
import org.example.entities.*;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.strings.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.tools.strings.PageName.*;

@Controller
@RequestMapping(value = "/add")
public class AddController {

    private final DAOStudentImpl daoStudent;
    private final DAOTeacherImpl daoTeacher;
    private final DAOGroupImpl daoGroup;
    private final DAOSubjectImpl daoSubject;
    private final DAOTeacherSubjectImpl daoTeacherSubject;
    private final DAOStudentSubjectImpl daoStudentSubject;
    private final DAOStudentTaskImpl daoStudentTask;
    private final DAOTaskImpl daoTask;

    @Autowired
    public AddController(DAOStudentImpl daoStudent, DAOTeacherImpl daoTeacher, DAOGroupImpl daoGroup, DAOSubjectImpl daoSubject, DAOTeacherSubjectImpl daoTeacherSubject, DAOStudentSubjectImpl daoStudentSubject, DAOStudentTaskImpl daoStudentTask, DAOTaskImpl daoTask) {
        this.daoStudent = daoStudent;
        this.daoTeacher = daoTeacher;
        this.daoGroup = daoGroup;
        this.daoSubject = daoSubject;
        this.daoTeacherSubject = daoTeacherSubject;
        this.daoStudentSubject = daoStudentSubject;
        this.daoStudentTask = daoStudentTask;
        this.daoTask = daoTask;
    }

    @RequestMapping(value = "/group")
    @GetMapping
    public ModelAndView addByGroup(@RequestParam("groupName") String groupName,
                                   @RequestParam("groupDescription") String groupDescription) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            daoGroup.addGroup(new Group(null, groupName, groupDescription));
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

    @RequestMapping(value = "/teacher")
    @GetMapping
    public ModelAndView addByTeacher(@RequestParam("firstName") String firstName,
                                     @RequestParam("loginName") String loginName,
                                     @RequestParam("lastName") String lastName,
                                     @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();
        Teacher teacher = new Teacher(
                null,
                loginName,
                firstName,
                lastName,
                password
        );

        try {
            daoTeacher.addTeacher(teacher);
            modelAndView.setViewName("redirect:/show/teacher-all");//
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
                                       @RequestParam("passProcGrade") String passProcGrade,
                                     @RequestParam("subjectDescription") String subjectDescription) {

        ModelAndView modelAndView = new ModelAndView();
        Subject subject = new Subject(
                null,
                subjectName,
                Integer.parseInt(maxGrade),
                Integer.parseInt(passProcGrade),
                subjectDescription
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

            //not best solution
            String lastStudentSubjectId = daoStudentSubject.getLastStudentSubjectId();
            StudentSubject lastStudentSubject = daoStudentSubject.getStudentSubjectById(lastStudentSubjectId);
            List<Task> taskList = daoTask.getTaskListBySubjectId(lastStudentSubject.getSubjectId());
            for (Task t: taskList) {
                daoStudentTask.addStudentTask(new StudentTask(
                        t.getTaskId(),
                        t.getSubjectId(),
                        lastStudentSubjectId,
                        0
                ));
            }

            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (SQLException | WrongEntityIdException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }

    @RequestMapping(value = "/task")
    @GetMapping
    public ModelAndView addByTaskId(@RequestParam("subjectId") String subjectId,
                                    @RequestParam("taskName") String taskName,
                                    @RequestParam("maxGrade") String maxGrade,
                                    @RequestParam("taskDescription") String taskDescription) {

        ModelAndView modelAndView = new ModelAndView();
        Task task = new Task(
                null,
                subjectId,
                taskName,
                Integer.parseInt(maxGrade),
                taskDescription
        );

        //add studentTask for all students of subject
        try {
            daoTask.addTask(task);
            daoSubject.actualizeMaxGrade(subjectId);
            //not best solution
            String lastTaskId = daoTask.getLastTaskId();
            Task lastTask = daoTask.getTaskById(lastTaskId);
            List<StudentSubject> studentSubjectList = daoStudentSubject.getStudentSubjectsBySubjectId(lastTask.getSubjectId());
            for (StudentSubject s: studentSubjectList) {
                daoStudentTask.addStudentTask(new StudentTask(
                        lastTaskId,
                        s.getSubjectId(),
                        s.getStudentSubjectId(),
                        0
                ));
            }

            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//
        } catch (SQLException | WrongEntityIdException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }

    //TO DO: chekc if need
    @RequestMapping(value = "/student-task")
    @GetMapping
    public ModelAndView addStudentTask(@RequestParam("studentSubjectId") String studentSubjectId,
                                    @RequestParam("taskId") String taskId,
                                    @RequestParam("subjectId") String subjectId,
                                    @RequestParam("grade") String grade) {

        ModelAndView modelAndView = new ModelAndView();
        StudentTask studentTask = new StudentTask(
                taskId,
                subjectId,
                studentSubjectId,
                Integer.parseInt(grade)
        );

        try {
            daoStudentTask.addStudentTask(studentTask);
            modelAndView.setViewName("redirect:/show/subject?subjectId=" + subjectId);//TO DO: if need
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            modelAndView.setViewName(ERROR_PAGE.getPageName());
        }

        return modelAndView;
    }
}
