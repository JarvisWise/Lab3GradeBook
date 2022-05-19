package org.example.dao.implementations;

import org.apache.log4j.Logger;
import org.example.dao.connection.Oracle;
import org.example.dao.interfaces.DAOGroup;
import org.example.entities.Group;
import org.example.entities.Student;
import org.example.entities.StudentSubject;
import org.example.tools.custom.exceptions.WrongEntityIdException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.example.tools.strings.Query.*;

@Repository
public class DAOGroupImpl extends Oracle implements DAOGroup {

    private static final Logger logger = Logger.getLogger(DAOGroupImpl.class);

    @Override
    public Group getGroupById(String id) throws WrongEntityIdException {
        try {
            connect();
            statement = connection.prepareStatement(
                    GROUP_BY_ID.getQuery());

            statement.setInt(1, Integer.parseInt(id));

            result = statement.executeQuery();
            if(result.next()) {
                return Group.parse(result);
            } else {
                throw new WrongEntityIdException("desc ");
            }
        } catch (SQLException | WrongEntityIdException e) {
            e.printStackTrace();
            throw new WrongEntityIdException("desc ", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addGroup(Group group) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_GROUP.getQuery());
            statement.setString(1, group.getGroupName());
            statement.setString(2, group.getGroupDescription());
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void addGroup(String groupName) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(ADD_GROUP.getQuery());
            statement.setString(1, groupName);
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void updateGroup(Group group) throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement(UPDATE_GROUP.getQuery());

            statement.setString(1, group.getGroupName());
            statement.setString(2, group.getGroupDescription());
            statement.setInt(3, Integer.parseInt(group.getGroupId()));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public void deleteGroup(String groupId) {
        try {
            connect();
            statement = connection.prepareStatement(DELETE_GROUP_BY_ID.getQuery());
            statement.setInt(1, Integer.parseInt(groupId));
            statement.execute();
        } catch (SQLException e) {
            logger.info("desc");
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Group> getAllGroups() throws WrongEntityIdException {
        try {
            connect();
            List<Group> list = new ArrayList<>();
            statement = connection.prepareStatement(ALL_GROUPS.getQuery());
            result = statement.executeQuery();
            while (result.next()) {
                list.add(Group.parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new WrongEntityIdException("desc", e);
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Group> searchGroupsByName(String text) throws SQLException, WrongEntityIdException {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        } else if (text.equals("*")) {
            return getAllGroups();
        }

        try {
            connect();
            List<Group> list = new ArrayList<>();
            statement = connection.prepareStatement(SEARCH_BY_GROUP_NAME.getQuery());
            statement.setString(1, text);
            result = statement.executeQuery();
            while (result.next()) {
                list.add(Group.parse(result));
            }
            return list;
        } catch (SQLException e) {
            logger.info("desc", e);
            throw new SQLException("desc", e);
        } finally {
            disconnect();
        }
    }
}
