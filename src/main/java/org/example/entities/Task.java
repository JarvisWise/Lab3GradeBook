package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Task {
    private String taskId;
    private String SubjectId;
    private String taskName;
    private int maxGrade;
    private String taskDescription;

    public Task() {

    }

    public Task(String taskId, String subjectId, String taskName, int maxGrade, String taskDescription) {
        this.taskId = taskId;
        this.SubjectId = subjectId;
        this.taskName = taskName;
        this.maxGrade = maxGrade;
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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

    public static Task parse(ResultSet result) throws SQLException {
        return new Task(
                result.getString("task_id"),
                result.getString("subject_id"),
                result.getString("task_name"),
                result.getInt("max_grade"),
                result.getString("task_description")
        );
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", SubjectId='" + SubjectId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", maxGrade=" + maxGrade +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}
