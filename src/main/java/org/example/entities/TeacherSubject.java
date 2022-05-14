package org.example.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

//mb not need but delete some time after
public class TeacherSubject {
    private String subjectId;
    private String teacherId;

    public TeacherSubject() {

    }

    public TeacherSubject(String subjectId, String teacherId) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
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

    public static TeacherSubject parse(ResultSet result) throws SQLException {
        return new TeacherSubject(
                result.getString("subject_id"),
                result.getString("teacher_id")
        );
    }

    @Override
    public String toString() {
        return "TeacherSubject{" +
                "subjectId='" + subjectId + '\'' +
                ", teacherId='" + teacherId +
                '}';
    }
}
