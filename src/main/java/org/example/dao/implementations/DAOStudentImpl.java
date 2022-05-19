package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOGroup;
import org.example.dao.interfaces.DAOStudent;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.StudentInfoSet;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOStudentImpl extends Oracle implements DAOStudent {

    private static final Logger logger = Logger.getLogger(DAOStudentImpl.class);

    @Override
    public Student getStudentById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    STUDENT_BY_ID.getQuery());

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();
            if(result.next()) {
                return Student.parse(result);
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
    public boolean isExistStudentByLogin(String loginName) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(
                    EXIST_STUDENT_BY_LOGIN.getQuery());

            statement.setString(1, loginName);
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
    public Student getStudentByLoginNameAndPassword(String studentLoginName, String password) throws WrongLoginDataException {
        try {
            connect();
            statement = connection.prepareStatement(
                    STUDENT_BY_LOGIN_NAME_AND_PASSWORD.getQuery());

            statement.setString(1, studentLoginName);
            statement.setString(2, password);

            result = statement.executeQuery();
            if(result.next()) {
                return Student.parse(result);
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
    public StudentInfoSet getStudentInfoSet(String studentId) throws WrongEntityIdException {
        DAOGroup daoGroup = new DAOGroupImpl();
        Student student = getStudentById(studentId);
        Student headman = null;
        if (student.getHeadman() != null) {
            headman = getStudentById(student.getHeadman());
        }
        Group group = null;
        if (student.getGroupId() != null) {
            group = daoGroup.getGroupById(student.getGroupId());
        }
        return new StudentInfoSet(student, group, headman);
    }

    @Override
    public List<StudentInfoSet> getStudentInfoSetList(List<Student> studentList) throws WrongEntityIdException {
        List<StudentInfoSet> studentInfoSetList =  new ArrayList<>();
        for (Student s: studentList) {
            // TO DO: mb add check
            studentInfoSetList.add(getStudentInfoSet(s.getStudentId()));
        }
        return studentInfoSetList;
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

            if (student.getHeadman() == null || student.getHeadman().isEmpty()) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, Integer.parseInt(student.getHeadman()));
            }

            statement.setString(2, student.getLoginName());
            statement.setString(3, student.getFirstName());
            statement.setString(4, student.getLastName());

            if (student.getGroupId() == null || student.getGroupId().isEmpty()) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, Integer.parseInt(student.getGroupId()));
            }

            statement.setString(6, student.getPassword());
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

            if (student.getHeadman() == null || student.getHeadman().isEmpty()) {
                statement.setNull(3, Types.INTEGER);
            } else {
                statement.setInt(3, Integer.parseInt(student.getHeadman()));
            }

            if (student.getGroupId() == null || student.getGroupId().isEmpty()) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, Integer.parseInt(student.getGroupId()));
            }

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
    public void updateGroupOfStudent(String studentId, String groupId) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_GROUP_OF_STUDENT.getQuery());

            if (groupId == null || groupId.isEmpty()) {
                statement.setNull(1, Types.INTEGER);
            } else {
                statement.setInt(1, Integer.parseInt(groupId));
            }
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
    public void deleteGroupOfStudent(String studentId) throws SQLException {
        updateGroupOfStudent(studentId, null);
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
                list.add(Student.parse(result));
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
                list.add(Student.parse(result));
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
    public List<Student> searchStudentsByFullName(String text) throws SQLException, WrongEntityIdException {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        } else if (text.equals("*")) {
            return getAllStudents();
        }

        try {
            connect();
            List<Student> list = new ArrayList<>();
            statement = connection.prepareStatement(SEARCH_BY_STUDENT_FULL_NAME.getQuery());
            statement.setString(1, text);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(Student.parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }
}
