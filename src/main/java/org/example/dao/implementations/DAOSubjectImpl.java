package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOSubject;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOSubjectImpl extends Oracle implements DAOSubject {

    private static final Logger logger = Logger.getLogger(DAOSubjectImpl.class);

    @Override
    public Subject parse(ResultSet result) throws SQLException {
        return new Subject(
                result.getString("subject_id"),
                result.getString("subject_name"),
                result.getInt("max_grade"),
                result.getInt("pass_proc_grade")
        );
    }

    @Override
    public Subject getSubjectById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    SUBJECT_BY_ID.getQuery());

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
    public List<Student> getStudentsBySubjectId(String subjectId) {
        return null;
    }

    @Override
    public void addSubject(Subject subject) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_SUBJECT.getQuery());
            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getMaxGrade());
            statement.setInt(3, subject.getPassProcGrade());
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateSubject(Subject subject) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_SUBJECT.getQuery());

            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getMaxGrade());
            statement.setInt(3, subject.getPassProcGrade());
            statement.setInt(4, Integer.parseInt(subject.getSubjectId()));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteSubject(String subjectId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_SUBJECT_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(subjectId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Subject> getAllSubjects() throws WrongEntityIdException {
        try {
            connect();
            List<Subject> list = new ArrayList<>();
            statement = connection.prepareStatement(ALL_SUBJECTS.getQuery());
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