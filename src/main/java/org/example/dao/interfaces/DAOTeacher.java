package org.example.dao.interfaces;

import org.example.entities.Teacher;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.example.tools.custom.exceptions.WrongLoginDataException;

import java.sql.SQLException;
import java.util.List;

public interface DAOTeacher {
    Teacher getTeacherById(String id) throws WrongEntityIdException;
    List<Teacher> getTeachersBySubjectId(String subjectId) throws WrongEntityIdException;
    Teacher getTeacherByLoginNameAndPassword(String teacherId, String password) throws WrongLoginDataException;
    void changeTeacherPassword(String teacherId, String password) throws SQLException;
    void addTeacher(Teacher teacher) throws SQLException;
    void updateTeacher(Teacher teacher) throws SQLException;
    void deleteTeacher(String teacherId);
}
