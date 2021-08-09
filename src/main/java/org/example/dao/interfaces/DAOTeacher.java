package org.example.dao.interfaces;

import org.example.entities.Student;
import org.example.entities.Task;
import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAOTeacher {
    Teacher parse(ResultSet result) throws SQLException;
    Teacher getTeacherById(String id) throws WrongEntityIdException;
    Teacher getTeacherByIdAndPassword(String teacherId, String password) throws WrongLoginDataException;
    void changeTeacherPassword(String teacherId, String password) throws SQLException;
    void addTeacher(Teacher teacher) throws SQLException;
    void updateTeacher(Teacher teacher) throws SQLException;
    void deleteTeacher(String teacherId);
}
