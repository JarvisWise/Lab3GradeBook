package org.example.dao.interfaces;

import org.example.entities.Task;

import java.sql.ResultSet;

public interface DAOTask {
    Task parse(ResultSet result);
    Task getTaskById(String id);
    void addTask(Task task);
    void updateTask(Task task);
    void deleteTask(Task task);
}
