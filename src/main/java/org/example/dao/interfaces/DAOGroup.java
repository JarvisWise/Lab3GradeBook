package org.example.dao.interfaces;

import org.example.entities.Group;
import org.example.entities.Student;

import java.sql.ResultSet;
import java.util.List;

public interface DAOGroup {
    Group parse(ResultSet result);
    Group getGroupById(String id);
    List<Student> getStudentsByGroupId(String groupId);
    void addGroup(Group group);
    void updateGroup(Group group);
    void deleteGroup(String groupId);
}
