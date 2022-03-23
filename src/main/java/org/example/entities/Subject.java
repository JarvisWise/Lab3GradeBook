package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class Subject implements Entity{
public class Subject {
    private String subjectId;
    private String subjectName;
    private int maxGrade;
    private int passProcGradeP;

    public Subject() {

    }

    public Subject(String subject_id, String subjectName, int maxGrade, int passProcGradeP) {
        this.subjectId = subject_id;
        this.subjectName = subjectName;
        this.maxGrade = maxGrade;
        this.passProcGradeP = passProcGradeP;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public int getPassProcGradeP() {
        return passProcGradeP;
    }

    public void setPassProcGradeP(int passProcGradeP) {
        this.passProcGradeP = passProcGradeP;
    }

    public static Subject parse(ResultSet result) throws SQLException {
        return new Subject(
                result.getString("subject_id"),
                result.getString("subject_name"),
                result.getInt("max_grade"),
                result.getInt("pass_proc_grade")
        );
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", maxGrade=" + maxGrade +
                ", passProcGrade=" + passProcGradeP +
                '}';
    }
}
