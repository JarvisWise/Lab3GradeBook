package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Group implements Entity{
public class Group{
    private String groupId;
    private String groupName;
    private String groupDescription;

    public Group() {

    }

    public Group(String groupId, String groupName,  String groupDescription) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
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
                result.getString("group_name"),
                result.getString("group_description")
        );
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                '}';
    }
}
