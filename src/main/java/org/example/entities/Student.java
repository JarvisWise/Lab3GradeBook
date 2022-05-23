package org.example.entities;

import org.example.tools.strings.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Student implements Entity{
public class Student extends User {
    private String studentId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String headman;
    private String groupId;

    public Student() {

    }

    public Student(String studentId, String email, String firstName, String lastName, String password, String headman, String groupId) {
        this.studentId = studentId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.headman = headman;
        this.groupId = groupId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return studentId;
    }

    @Override
    public String getRole() {
        return Role.STUDENT.getRole();
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

    public String getHeadman() {
        return headman;
    }

    public void setHeadman(String headman) {
        this.headman = headman;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public static Student parse(ResultSet result) throws SQLException {
        return new Student(
                result.getString("student_id"),
                result.getString("email"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("password"),
                result.getString("headman"),
                result.getString("group_id")
        );
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", email='" + email + '\'' +
                ", fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", headman='" + headman + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
