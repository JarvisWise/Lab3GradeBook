package org.example.entities;

//public class Task implements Entity{
public class Task {
    private String taskId;
    private String SubjectId;
    private String studentSubjectId;
    private String taskName;
    private int maxGrade;
    private int grade;

    public Task() {

    }

    public Task(String taskId, String subjectId, String studentSubjectId, String taskName, int maxGrade, int grade) {
        this.taskId = taskId;
        SubjectId = subjectId;
        this.studentSubjectId = studentSubjectId;
        this.taskName = taskName;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", SubjectId='" + SubjectId + '\'' +
                ", studentSubjectId='" + studentSubjectId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", maxGrade=" + maxGrade +
                ", grade=" + grade +
                '}';
    }
}
