package org.example.dao.interfaces;

import org.example.entities.Group;
import org.example.entities.Student;
import org.example.tools.custom.exceptions.WrongEntityIdException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOGroup {
    Group getGroupById(String id) throws WrongEntityIdException;
    void addGroup(Group group) throws SQLException;
    void updateGroup(Group group) throws SQLException;
    void deleteGroup(String groupId);
    List<Group> getAllGroups() throws WrongEntityIdException;
    List<Group> searchGroupsByName(String text) throws SQLException, WrongEntityIdException;
}
