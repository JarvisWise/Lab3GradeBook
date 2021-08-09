package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.DAOEntity;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOTask;
import org.example.entities.Entity;
import org.example.entities.Task;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class DAOTaskImpl extends Oracle implements DAOTask {

    private static final Logger logger = Logger.getLogger(DAOTaskImpl.class);

    @Override
    public Task parse(ResultSet result) {
        return null;
    }

    @Override
    public Task getTaskById(String id) {
        return null;
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void deleteTask(Task task) {

    }
}