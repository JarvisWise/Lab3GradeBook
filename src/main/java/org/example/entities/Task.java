package org.example.entities;

public class Task implements Entity{
    private String taskId;
    private String SubjectId;
    private String studentSubjectId;
    private String name;
    private int maxGrade;
    private int grade;

    public Task() {

    }

    public Task(String taskId, String subjectId, String studentSubjectId, String name, int maxGrade, int grade) {
        this.taskId = taskId;
        SubjectId = subjectId;
        this.studentSubjectId = studentSubjectId;
        this.name = name;
        this.maxGrade = maxGrade;
        this.grade = grade;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(String subjectId) {
        SubjectId = subjectId;
    }

    public String getStudentSubjectId() {
        return studentSubjectId;
    }

    public void setStudentSubjectId(String studentSubjectId) {
        this.studentSubjectId = studentSubjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
