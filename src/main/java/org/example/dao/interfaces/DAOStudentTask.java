package org.example.dao.interfaces;

import org.example.entities.*;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface DAOStudentTask {
    StudentTask getStudentTaskById(String studentSubjectId, String taskId) throws WrongEntityIdException;
    List<StudentTask> getStudentTaskByStudentSubjectId(String studentSubjectId) throws WrongEntityIdException;
    void addStudentTask(StudentTask studentTask) throws SQLException;
    void updateStudentTask(StudentTask studentTask);
    void updateStudentTaskGrade(String taskId, String  studentSubjectId, int newGrade, String teacherId) throws SQLException;
    void deleteStudentTask(String taskId, String studentTaskId);
    void deleteStudentTasksBySubjectId(String subjectId);
    void deleteStudentTasksByStudentSubjectId(String studentSubjectId);
    void deleteStudentTasksByTaskId(String taskId);
    void deleteStudentTasksByStudentId(String studentId);
    void deleteByTeacherId(String teacherId);
    HashMap<Task, StudentTask> getStudentTasksInfoByStudentSubjectId(String studentSubjectId) throws WrongEntityIdException;
    StudentTaskInfoSet getStudentTaskInfoSet(StudentTask studentTask) throws WrongEntityIdException;
    List<StudentTaskInfoSet> getStudentTaskInfoSetList(String studentSubjectId) throws WrongEntityIdException;
}
