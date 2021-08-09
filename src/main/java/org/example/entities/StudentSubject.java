package org.example.entities;

//public class StudentSubject implements Entity{
public class StudentSubject {
    private String studentId;
    private String subjectId;
    private String teacherId;
    private int totalGrade;

    public StudentSubject() {

    }

    public StudentSubject(String studentId, String subjectId, String teacherId, int totalGrade) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(int totalGrade) {
        this.totalGrade = totalGrade;
    }
}
