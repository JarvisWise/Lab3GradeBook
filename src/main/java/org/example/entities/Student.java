package org.example.entities;

//public class Student implements Entity{
public class Student {
    private String studentId;
    private String fistName;
    private String lastName;
    private String password;
    private String headman;
    private String groupId;

    public Student() {

    }

    public Student(String studentId, String fistName, String lastName, String password, String headman, String groupId) {
        this.studentId = studentId;
        this.fistName = fistName;
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

    public String getFirstName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
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
}
