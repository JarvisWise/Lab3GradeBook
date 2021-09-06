package org.example.dao.interfaces;

import org.example.entities.Student;
import org.example.entities.StudentSubject;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//?
public interface DAOStudentSubject {
    StudentSubject parse(ResultSet result) throws SQLException;
    StudentSubject getStudentSubjectById(String id) throws WrongEntityIdException;
    List<StudentSubject> getStudentSubjectsByStudentId(String studentId) throws WrongEntityIdException;
    void addStudentSubject(StudentSubject studentSubject) throws SQLException;
    void updateStudentSubject(StudentSubject studentSubject) throws SQLException;
    void deleteStudentSubject(String studentSubjectId);
}
