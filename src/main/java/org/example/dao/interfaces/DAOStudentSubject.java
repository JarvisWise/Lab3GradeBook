package org.example.dao.interfaces;

import org.example.entities.Student;
import org.example.entities.StudentSubject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//?
public interface DAOStudentSubject {
    StudentSubject parse(ResultSet result) throws SQLException;
    StudentSubject getStudentById(String id);
    List<StudentSubject> getStudentSubjectsByStudentId(String studentId) throws SQLException;
    void addStudent(StudentSubject studentSubject);
    void updateStudent(StudentSubject studentSubject);
    void deleteStudent(StudentSubject studentSubject);
}
