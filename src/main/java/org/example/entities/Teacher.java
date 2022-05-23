package org.example.entities;

import org.example.tools.strings.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Teacher implements Entity{
public class Teacher extends User {
    private String teacherId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public Teacher() {

    }

    public Teacher(String teacherId, String email, String firstName, String lastName, String password) {
        this.teacherId = teacherId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                result.getString("email"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password")
        );
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
