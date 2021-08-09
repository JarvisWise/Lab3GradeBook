package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.DAOEntity;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOTeacher;
import org.example.entities.Entity;
import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOTeacherImpl extends Oracle implements DAOTeacher {

    private static final Logger logger = Logger.getLogger(DAOTeacherImpl.class);

    @Override
    public Teacher parse(ResultSet result) throws SQLException {
        return new Teacher(
                result.getString("teacher_id"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password")
        );
    }

    @Override
    public Teacher getTeacherById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    TEACHER_BY_ID.getQuery());

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();
            if(result.next()) {
                return parse(result);
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
    public Teacher getTeacherByIdAndPassword(String teacherId, String password) throws WrongLoginDataException {
        try {
            connect();
            statement = connection.prepareStatement(
                    TEACHER_BY_ID_PASSWORD.getQuery());

            statement.setInt(1, Integer.parseInt(teacherId));
            statement.setString(2, password);

            result = statement.executeQuery();
            if(result.next()) {
                return parse(result);
            } else {
                throw new WrongLoginDataException("desc ");
            }
        } catch (SQLException | WrongLoginDataException e) {
            e.printStackTrace();
            throw new WrongLoginDataException("desc ", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void changeTeacherPassword(String teacherId, String password) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(CHANGE_TEACHER_PASSWORD.getQuery());

            statement.setString(1, password);
            statement.setInt(2, Integer.parseInt(teacherId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addTeacher(Teacher teacher) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_TEACHER.getQuery());
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, "password"); //default password
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_TEACHER.getQuery());

            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setInt(3, Integer.parseInt(teacher.getTeacherId()));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteTeacher(String teacherId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_TEACHER_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(teacherId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }
}
