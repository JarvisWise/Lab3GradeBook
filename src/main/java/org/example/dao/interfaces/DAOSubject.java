package org.example.dao.interfaces;

import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOSubject {
    Subject getSubjectById(String id) throws WrongEntityIdException;
    List<Student> getStudentsBySubjectId(String subjectId);
    void addSubject(Subject subject) throws SQLException;
    void updateSubject(Subject subject) throws SQLException;
    void deleteSubject(String subjectId);
    List<Subject> getAllSubjects() throws WrongEntityIdException;
    List<Subject> searchSubjectsByName(String text) throws SQLException, WrongEntityIdException;
}
