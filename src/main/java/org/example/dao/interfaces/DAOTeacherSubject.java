package org.example.dao.interfaces;

import org.example.entities.Subject;
import org.example.entities.TeacherSubject;

import java.sql.SQLException;

public interface DAOTeacherSubject {
    void addTeacherSubject(TeacherSubject teacherSubject) throws SQLException;
    void deleteTeacherSubject(TeacherSubject teacherSubject);
}
