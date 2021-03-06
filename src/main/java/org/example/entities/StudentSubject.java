package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class StudentSubject implements Entity{
public class StudentSubject {
    private String studentSubjectId;
    private String studentId;
    private String subjectId;
    //private String teacherId;//delete this field and move it to teacher_subject
    private int totalGrade;

    public StudentSubject() {

    }

    public StudentSubject(String studentSubjectId, String studentId, String subjectId, int totalGrade) {
        this.studentSubjectId = studentSubjectId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.totalGrade = totalGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(int totalGrade) {
        this.totalGrade = totalGrade;
    }

    public String getStudentSubjectId() {
        return studentSubjectId;
    }

    public void setStudentSubjectId(String studentSubjectId) {
        this.studentSubjectId = studentSubjectId;
    }

    public static StudentSubject parse(ResultSet result) throws SQLException {
        return new StudentSubject(
                result.getString("student_subject_id"),
                result.getString("student_id"),
                result.getString("subject_id"),
                result.getInt("total_grade")
        );
    }

    @Override
    public String toString() {
        return "StudentSubject{" +
                "studentSubjectId='" + studentSubjectId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", totalGrade=" + totalGrade +
                '}';
    }
}
