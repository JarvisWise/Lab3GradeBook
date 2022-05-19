package org.example.dao.interfaces;

import org.example.entities.StudentTask;
import org.example.entities.Task;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface DAOStudentTask {
    StudentTask getStudentTaskById(String taskId, String studentTaskId) throws WrongEntityIdException;
    List<StudentTask> getStudentTaskByStudentSubjectId(String studentSubjectId) throws WrongEntityIdException;
    void addStudentTask(StudentTask studentTask) throws SQLException;
    void updateStudentTask(StudentTask studentTask);
    void updateStudentTaskGrade(String taskId, String  studentSubjectId, int newGrade) throws SQLException;
    void deleteStudentTask(String taskId, String studentTaskId);
    void deleteStudentTasksBySubjectId(String subjectId);
    void deleteStudentTasksByStudentSubjectId(String studentSubjectId);
    void deleteStudentTasksByTaskId(String taskId);
    HashMap<Task, StudentTask> getStudentTasksInfoByStudentSubjectId(String studentSubjectId) throws WrongEntityIdException;
}
