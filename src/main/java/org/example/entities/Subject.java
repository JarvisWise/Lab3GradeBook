package org.example.entities;

//public class Subject implements Entity{
public class Subject {
    private String subjectId;
    private String subjectName;
    private int maxGrade;
    private int passProcGrade;

    public Subject() {

    }

    public Subject(String subject_id, String subjectName, int maxGrade, int passProcGrade) {
        this.subjectId = subject_id;
        this.subjectName = subjectName;
        this.maxGrade = maxGrade;
        this.passProcGrade = passProcGrade;
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

    public int getPassProcGrade() {
        return passProcGrade;
    }

    public void setPassProcGrade(int passProcGrade) {
        this.passProcGrade = passProcGrade;
    }
}
