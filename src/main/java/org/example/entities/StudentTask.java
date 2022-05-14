package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Task implements Entity{
public class StudentTask {
    private String taskId;
    private String SubjectId;
    private String studentSubjectId;
    private int grade;

    public StudentTask() {

    }

    public StudentTask(String taskId, String subjectId, String studentSubjectId, int grade) {
        this.taskId = taskId;
        this.SubjectId = subjectId;
        this.studentSubjectId = studentSubjectId;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public static StudentTask parse(ResultSet result) throws SQLException {
        return new StudentTask(
                result.getString("subject_id"),
                result.getString("task_id"),
                result.getString("student_subject_id"),
                result.getInt("grade")
        );
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", SubjectId='" + SubjectId + '\'' +
                ", studentSubjectId='" + studentSubjectId + '\'' +
                ", grade=" + grade +
                '}';
    }
}
