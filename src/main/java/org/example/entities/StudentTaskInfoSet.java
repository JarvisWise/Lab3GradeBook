package org.example.entities;

public class StudentTaskInfoSet {
    StudentTask studentTask;
    Task task;
    Teacher teacher;

    public StudentTaskInfoSet(StudentTask studentTask, Task task, Teacher teacher) {
        this.studentTask = studentTask;
        this.task = task;
        this.teacher = teacher;
    }

    public StudentTask getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(StudentTask studentTask) {
        this.studentTask = studentTask;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
