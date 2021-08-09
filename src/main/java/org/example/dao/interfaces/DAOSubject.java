package org.example.dao.interfaces;

import org.example.entities.Student;
import org.example.entities.Subject;

import java.sql.ResultSet;
import java.util.List;

public interface DAOSubject {
    Subject parse(ResultSet result);
    Subject getSubjectById(String id);
    List<Student> getStudentsBySubjectId(String subjectId);
    void addSubject(Subject subject);
    void updateSubject(Subject subject);
    void deleteSubject(Subject subject);
}
