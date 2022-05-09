package org.example.dao.interfaces;

import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.StudentInfoSet;
import org.example.entities.StudentSubject;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOStudent {
    Student getStudentById(String id) throws WrongEntityIdException;
    Student getStudentByLoginNameAndPassword(String studentId, String password) throws WrongLoginDataException;
    StudentInfoSet getStudentInfoSet(String studentId) throws WrongEntityIdException;
    void changeStudentPassword(String studentId, String password) throws SQLException;
    void addStudent(Student student) throws SQLException;
    void updateStudent(Student student) throws SQLException;
    void deleteStudent(String studentId);
    List<Student> getAllStudents() throws WrongEntityIdException;
    List<Student> getStudentsByGroupId(String groupId) throws WrongEntityIdException;
    List<Student> searchStudentsByFullName(String text) throws SQLException, WrongEntityIdException;
}
