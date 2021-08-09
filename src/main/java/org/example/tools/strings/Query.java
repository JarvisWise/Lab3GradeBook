package org.example.tools.strings;

public enum Query {
    STUDENT_BY_ID_PASSWORD("SELECT * FROM student WHERE student_id = ? AND password = ?"),
    TEACHER_BY_ID_PASSWORD("SELECT * FROM teacher WHERE teacher_id = ? AND password = ?"),
    STUDENT_BY_ID("SELECT * FROM student WHERE student_id = ?"),
    TEACHER_BY_ID("SELECT * FROM teacher WHERE teacher_id = ?"),
    DELETE_STUDENT_BY_ID("DELETE FROM student WHERE student_id = ?"),
    DELETE_TEACHER_BY_ID("DELETE FROM teacher WHERE teacher_id = ?"),
    ADD_STUDENT("INSERT INTO student VALUES (student_id_seq.nextval, ?, ?, ?, ?, ?)"),
    ADD_TEACHER("INSERT INTO teacher VALUES (teacher_id_seq.nextval, ?, ?, ?)"),
    UPDATE_STUDENT("UPDATE student SET first_name=?, last_name=?, headman=?, group_id=?, WHERE student_id=?"),
    UPDATE_TEACHER("UPDATE teacher SET first_name=?, last_name=? WHERE teacher_id=?"),
    CHANGE_STUDENT_PASSWORD("UPDATE student SET password=? WHERE student_id=?"),
    CHANGE_TEACHER_PASSWORD("UPDATE teacher SET password=? WHERE teacher_id=?"),
    STUDENT_SUBJECT_LIST_BY_STUDENT_ID("SELECT * FROM student_subject WHERE student_id = ?");

    public final String query;

    private Query(String query) {
        this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
