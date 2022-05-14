package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOTeacherSubject;
import org.example.entities.Subject;
import org.example.entities.TeacherSubject;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOTeacherSubjectImpl extends Oracle implements DAOTeacherSubject {

    private static final Logger logger = Logger.getLogger(DAOStudentSubjectImpl.class);

    @Override
    public void addTeacherSubject(TeacherSubject teacherSubject) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_TEACHER_SUBJECT.getQuery());
            statement.setInt(1, Integer.parseInt(teacherSubject.getSubjectId()));
            statement.setInt(2, Integer.parseInt(teacherSubject.getTeacherId()));

            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteTeacherSubject(TeacherSubject teacherSubject) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_TEACHER_SUBJECT_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(teacherSubject.getSubjectId()));
            statement.setInt(2, Integer.parseInt(teacherSubject.getTeacherId()));

            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }
}
