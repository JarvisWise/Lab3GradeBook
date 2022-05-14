package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOStudentSubject;
import org.example.entities.Student;
import org.example.entities.StudentSubject;
import org.example.entities.Subject;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOStudentSubjectImpl extends Oracle implements DAOStudentSubject {

    private static final Logger logger = Logger.getLogger(DAOStudentSubjectImpl.class);

    @Override
    public StudentSubject getStudentSubjectById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    STUDENT_SUBJECT_BY_ID.getQuery());

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();
            if(result.next()) {
                return StudentSubject.parse(result);
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
    public List<StudentSubject> getStudentSubjectsByStudentId(String studentId) throws WrongEntityIdException {
        try {
            connect();
            List<StudentSubject> list = new ArrayList<>();
            statement = connection.prepareStatement(STUDENT_SUBJECT_LIST_BY_STUDENT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentId));
            result = statement.executeQuery();
            while (result.next()) {
                list.add(StudentSubject.parse(result));
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
    public List<StudentSubject> getStudentSubjectsBySubjectId(String subjectId) throws WrongEntityIdException {
        try {
            connect();
            List<StudentSubject> list = new ArrayList<>();
            statement = connection.prepareStatement(STUDENT_SUBJECT_LIST_BY_SUBJECT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(subjectId));
            result = statement.executeQuery();
            while (result.next()) {
                list.add(StudentSubject.parse(result));
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
    public HashMap<Student, StudentSubject> getStudentsInfoBySubjectId(String subjectId) throws WrongEntityIdException {
        try {
            connect();
            HashMap<Student, StudentSubject> studentSubjectMap = new HashMap<>();
            statement = connection.prepareStatement(STUDENT_SUBJECT_MAP_BY_SUBJECT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(subjectId));
            result = statement.executeQuery();
            while (result.next()) {
                studentSubjectMap.put(Student.parse(result), StudentSubject.parse(result));
            }
            return studentSubjectMap;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public HashMap<Subject, StudentSubject> getSubjectsInfoByStudentId(String studentId) throws WrongEntityIdException {
        try {
            connect();
            HashMap<Subject, StudentSubject> studentSubjectMap = new HashMap<>();
            statement = connection.prepareStatement(STUDENT_SUBJECT_MAP_BY_STUDENT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentId));
            result = statement.executeQuery();
            while (result.next()) {
                studentSubjectMap.put(Subject.parse(result), StudentSubject.parse(result));
            }
            return studentSubjectMap;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addStudentSubject(StudentSubject studentSubject) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_STUDENT_SUBJECT.getQuery());
            statement.setInt(1, studentSubject.getTotalGrade());
            statement.setInt(2, Integer.parseInt(studentSubject.getStudentId()));
            statement.setInt(3, Integer.parseInt(studentSubject.getSubjectId()));
            statement.setInt(4, Integer.parseInt(studentSubject.getTeacherId()));

            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateStudentSubject(StudentSubject studentSubject) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_STUDENT_SUBJECT.getQuery());

            statement.setInt(1, studentSubject.getTotalGrade());
            statement.setInt(2, Integer.parseInt(studentSubject.getStudentId()));
            statement.setInt(3, Integer.parseInt(studentSubject.getSubjectId()));
            statement.setInt(4, Integer.parseInt(studentSubject.getTeacherId()));
            statement.setInt(4, Integer.parseInt(studentSubject.getStudentSubjectId()));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentSubject(String studentSubjectId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_SUBJECT_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentSubjectId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteStudentSubject(String studentId, String subjectId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_STUDENT_SUBJECT_BY_IDS.getQuery());
            statement.setInt(1, Integer.parseInt(studentId));
            statement.setInt(2, Integer.parseInt(subjectId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }
}
