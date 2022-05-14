package org.example.entities;

public class StudentInfoSet {
    Student student;
    Group group;
    Student headman;

    public StudentInfoSet(Student student, Group group, Student headman) {
        this.student = student;
        this.group = group;
        this.headman = headman;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Student getHeadman() {
        return headman;
    }

    public void setHeadman(Student headman) {
        this.headman = headman;
    }
}
