package org.example.entities;

import org.example.tools.strings.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Teacher implements Entity{
public class Teacher extends User {
    private String teacherId;
    private String loginName;
    private String firstName;
    private String lastName;
    private String password;

    public Teacher() {

    }

    public Teacher(String teacherId, String loginName, String firstName, String lastName, String password) {
        this.teacherId = teacherId;
        this.loginName = loginName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUserId() {
        return teacherId;
    }

    @Override
    public String getRole() {
        return Role.TEACHER.getRole();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Teacher parse(ResultSet result) throws SQLException {
        return new Teacher(
                result.getString("teacher_id"),
                result.getString("login_name"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password")
        );
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
