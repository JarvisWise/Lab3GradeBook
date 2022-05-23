package org.example.dao.interfaces;

import org.example.entities.Student;
import org.example.entities.StudentSubject;
import org.example.entities.Subject;
import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

//?
public interface DAOStudentSubject {
    StudentSubject getStudentSubjectById(String id) throws WrongEntityIdException;
    List<StudentSubject> getStudentSubjectsByStudentId(String studentId) throws WrongEntityIdException;
    List<StudentSubject> getStudentSubjectsBySubjectId(String subjectId) throws WrongEntityIdException;
    StudentSubject getStudentSubjectBySubjectIdAndStudentId(String subjectId, String studentId) throws WrongEntityIdException;
    HashMap<Student, StudentSubject> getStudentsInfoBySubjectId(String subjectId) throws WrongEntityIdException;
    HashMap<Subject, StudentSubject> getSubjectsInfoByStudentId(String studentId) throws WrongEntityIdException;
    void addStudentSubject(StudentSubject studentSubject) throws SQLException;
    void updateStudentSubject(StudentSubject studentSubject) throws SQLException;
    void actualizeTotalGrade(String studentSubjectId) throws SQLException;
    void deleteStudentSubject(String studentSubjectId);
    void deleteStudentSubject(String studentId, String subjectId);
    void deleteStudentSubjectsBySubjectId(String subjectId);
    void deleteStudentSubjectsByStudentId(String studentId);
    String getLastStudentSubjectId() throws WrongEntityIdException;
}
