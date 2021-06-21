package org.example.entities;

public class Subject implements Entity{
    private String subject_id;
    private String subjectName;
    private int maxGrade;
    private int passProcGrade;

    public Subject() {

    }

    public Subject(String subject_id, String subjectName, int maxGrade, int passProcGrade) {
        this.subject_id = subject_id;
        this.subjectName = subjectName;
        this.maxGrade = maxGrade;
        this.passProcGrade = passProcGrade;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
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
