package org.example.dao.interfaces;

import org.example.entities.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAOTask {
    Task parse(ResultSet result) throws SQLException;
    Task getTaskById(String id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(String taskId);
}
