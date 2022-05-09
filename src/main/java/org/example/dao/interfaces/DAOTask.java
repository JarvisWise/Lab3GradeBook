package org.example.dao.interfaces;

import org.example.entities.StudentTask;
import org.example.entities.Task;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.SQLException;

public interface DAOTask {
    Task getTaskById(String taskId) throws WrongEntityIdException;
    void addTask(Task task) throws SQLException;
    void updateTask(Task task);
    void updateTaskName(String taskId, String newName) throws SQLException;
    void updateTaskMaxGrade(String taskId, int newMaxGrade) throws SQLException;
    void deleteTask(String taskId);
}
