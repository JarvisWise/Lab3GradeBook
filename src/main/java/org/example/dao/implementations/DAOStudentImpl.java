package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOStudent;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOStudentImpl extends Oracle implements DAOStudent {

    private static final Logger logger = Logger.getLogger(DAOStudentImpl.class);

    @Override
    public Student parse(ResultSet result) throws SQLException {
        return new Student(
                result.getString("student_id"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password"),
                result.getString("headman"),
                result.getString("group_id")
        );
    }

    @Override
    public Student getStudentById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    STUDENT_BY_ID.getQuery());

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
    public Student getStudentByIdAndPassword(String studentId, String password) throws WrongLoginDataException {
        try {
            connect();
            statement = connection.prepareStatement(
                    STUDENT_BY_ID_PASSWORD.getQuery());

            statement.setInt(1, Integer.parseInt(studentId));
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
    public void changeStudentPassword(String studentId, String password) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(CHANGE_STUDENT_PASSWORD.getQuery());

            statement.setString(1, password);
            statement.setInt(2, Integer.parseInt(studentId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_STUDENT.getQuery());
            statement.setInt(1, Integer.parseInt(student.getHeadman()));
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, Integer.parseInt(student.getGroupId()));
            statement.setString(5, "password"); //default password
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_STUDENT.getQuery());

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, Integer.parseInt(student.getHeadman()));
            statement.setInt(4, Integer.parseInt(student.getGroupId()));
            statement.setInt(5, Integer.parseInt(student.getStudentId()));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Student> getStudentsByGroupId(String groupId) throws WrongEntityIdException {
        try {
            connect();
            List<Student> list = new ArrayList<>();
            statement = connection.prepareStatement(STUDENTS_BY_GROUP_ID.getQuery());
            statement.setInt(1, Integer.parseInt(groupId));
            result = statement.executeQuery();
            while (result.next()) {
                list.add(parse(result));
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
    public List<Student> getAllStudents() throws WrongEntityIdException {
        try {
            connect();
            List<Student> list = new ArrayList<>();
            statement = connection.prepareStatement(ALL_STUDENTS.getQuery());
            result = statement.executeQuery();
            while (result.next()) {
                list.add(parse(result));
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
