package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.DAOEntity;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOSubject;
import org.example.entities.Entity;
import org.example.entities.Student;
import org.example.entities.Subject;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class DAOSubjectImpl extends Oracle implements DAOSubject {

    private static final Logger logger = Logger.getLogger(DAOSubjectImpl.class);

    @Override
    public Subject parse(ResultSet result) {
        return null;
    }

    @Override
    public Subject getSubjectById(String id) {
        return null;
    }

    @Override
    public List<Student> getStudentsBySubjectId(String subjectId) {
        return null;
    }

    @Override
    public void addSubject(Subject subject) {

    }

    @Override
    public void updateSubject(Subject subject) {

    }

    @Override
    public void deleteSubject(Subject subject) {

    }
}