package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOTeacher;
import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOTeacherImpl extends Oracle implements DAOTeacher {

    private static final Logger logger = Logger.getLogger(DAOTeacherImpl.class);

    @Override
    public Teacher getTeacherById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    TEACHER_BY_ID.getQuery());

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();
            if(result.next()) {
                return Teacher.parse(result);
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
    public boolean isExistTeacherByEmail(String email) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(
                    EXIST_TEACHER_BY_EMAIL.getQuery());

            statement.setString(1, email);
            result = statement.executeQuery();
            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("desc ", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Teacher> getTeachersBySubjectId(String subjectId) throws WrongEntityIdException {
        try {
            connect();
            List<Teacher> list = new ArrayList<>();
            statement = connection.prepareStatement(TEACHER_LIST_BY_SUBJECT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(subjectId));
            result = statement.executeQuery();
            while (result.next()) {
                list.add(Teacher.parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public Teacher getTeacherByEmailAndPassword(String email, String password) throws WrongLoginDataException {
        try {
            connect();
            statement = connection.prepareStatement(
                    TEACHER_BY_EMAIL_AND_PASSWORD.getQuery());

            statement.setString(1, email);
            statement.setString(2, password);

            result = statement.executeQuery();
            if(result.next()) {
                return Teacher.parse(result);
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
            statement.setString(1, teacher.getEmail());
            statement.setString(2, teacher.getFirstName());
            statement.setString(3, teacher.getLastName());
            statement.setString(4, teacher.getPassword());
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

            statement.setString(1, teacher.getEmail());
            statement.setString(2, teacher.getFirstName());
            statement.setString(3, teacher.getLastName());
            statement.setInt(4, Integer.parseInt(teacher.getTeacherId()));
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

    @Override
    public List<Teacher> getAllTeachers() throws WrongEntityIdException {
        try {
            connect();
            List<Teacher> list = new ArrayList<>();
            statement = connection.prepareStatement(ALL_TEACHERS.getQuery());
            result = statement.executeQuery();
            while (result.next()) {
                list.add(Teacher.parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }
}
