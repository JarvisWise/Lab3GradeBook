package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Group implements Entity{
public class Group{
    private String groupId;
    private String groupName;

    public Group() {

    }

    public Group(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public  static Group parse(ResultSet result) throws SQLException {
        return new Group(
                result.getString("group_id"),
                result.getString("group_name")
        );
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
