package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOTask;
import org.example.entities.Task;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOTaskImpl extends Oracle implements DAOTask {

    private static final Logger logger = Logger.getLogger(DAOTaskImpl.class);

    @Override
    public Task getTaskById(String taskId) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    TASK_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(taskId));

            result = statement.executeQuery();
            if(result.next()) {
                return Task.parse(result);
            } else {
                throw new WrongEntityIdException("desc ");
            }
        } catch (SQLException | WrongEntityIdException e) {
            e.printStackTrace();
            throw new WrongEntityIdException("desc ", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addTask(Task task) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_TASK.getQuery());
            statement.setInt(1, Integer.parseInt(task.getSubjectId()));
            statement.setInt(2, Integer.parseInt(task.getTaskName()));
            statement.setInt(3, task.getMaxGrade());

            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateTask(Task task) {
        //mb remove
    }

    @Override
    public void updateTaskName(String taskId, String newName) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_TASK_NAME.getQuery());

            statement.setInt(1, Integer.parseInt(newName));
            statement.setInt(2, Integer.parseInt(taskId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateTaskMaxGrade(String taskId, int newMaxGrade) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_TASK_MAX_GRADE.getQuery());

            statement.setInt(1, newMaxGrade);
            statement.setInt(2, Integer.parseInt(taskId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteTask(String taskId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_TASK_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(taskId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }
}
