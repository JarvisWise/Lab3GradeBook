package org.example.dao.interfaces;

import org.example.entities.StudentTask;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.SQLException;

public interface DAOStudentTask {
    StudentTask getStudentTaskById(String taskId, String studentTaskId) throws WrongEntityIdException;
    void addStudentTask(StudentTask studentTask) throws SQLException;
    void updateStudentTask(StudentTask studentTask);
    void updateStudentTaskGrade(String taskId, String  studentSubjectId, int newGrade) throws SQLException;
    void deleteStudentTask(String taskId, String studentTaskId);
}
