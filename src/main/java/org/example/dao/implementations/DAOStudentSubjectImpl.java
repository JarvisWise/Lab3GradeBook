package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOStudentSubject;
import org.example.entities.StudentSubject;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.tools.strings.Query.STUDENT_SUBJECT_LIST_BY_STUDENT_ID;

@Repository
public class DAOStudentSubjectImpl extends Oracle implements DAOStudentSubject {

    private static final Logger logger = Logger.getLogger(DAOStudentSubjectImpl.class);

    private String studentId;
    private String subjectId;
    private String teacherId;
    private int totalGrade;

    @Override
    public StudentSubject parse(ResultSet result) throws SQLException {
        return new StudentSubject(
                result.getString("student_id"),
                result.getString("subject_id"),
                result.getString("teacher_id"),
                result.getInt("total_grade")
        );
    }

    @Override
    public StudentSubject getStudentById(String id) {
        return null;
    }

    @Override
    public List<StudentSubject> getStudentSubjectsByStudentId(String studentId) throws SQLException {
        try {
            connect();
            List<StudentSubject> list = new ArrayList<>();
            statement = connection.prepareStatement(STUDENT_SUBJECT_LIST_BY_STUDENT_ID.getQuery());
            statement.setInt(1, Integer.parseInt(studentId));
            result = statement.executeQuery();
            while (result.next()) {
                list.add(parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addStudent(StudentSubject studentSubject) {

    }

    @Override
    public void updateStudent(StudentSubject studentSubject) {

    }

    @Override
    public void deleteStudent(StudentSubject studentSubject) {

    }
}
