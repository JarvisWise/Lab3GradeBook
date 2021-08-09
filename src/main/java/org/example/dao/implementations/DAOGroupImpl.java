package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.DAOEntity;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOGroup;
import org.example.entities.Entity;
import org.example.entities.Group;
import org.example.entities.Student;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class DAOGroupImpl extends Oracle implements DAOGroup {

    private static final Logger logger = Logger.getLogger(DAOGroupImpl.class);

    @Override
    public Group parse(ResultSet result) {
        return null;
    }

    @Override
    public Group getGroupById(String id) {
        return null;
    }

    @Override
    public List<Student> getStudentsByGroupId(String groupId) {
        return null;
    }

    @Override
    public void addGroup(Group group) {

    }

    @Override
    public void updateGroup(Group group) {

    }

    @Override
    public void deleteGroup(String groupId) {

    }
}
