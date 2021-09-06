package org.example.tools.strings;

public enum Query {
    ALL_STUDENTS("SELECT * FROM student"),
    ALL_SUBJECTS("SELECT * FROM subject"),
    ALL_GROUPS("SELECT * FROM group"),
    STUDENTS_BY_GROUP_ID("SELECT * FROM student WHERE group_id = ?"),
    STUDENT_BY_ID_PASSWORD("SELECT * FROM student WHERE student_id = ? AND password = ?"),
    TEACHER_BY_ID_PASSWORD("SELECT * FROM teacher WHERE teacher_id = ? AND password = ?"),
    SUBJECT_BY_ID("SELECT * FROM subject WHERE subject_id = ?"),
    STUDENT_SUBJECT_BY_ID("SELECT * FROM student_subject WHERE student_subject_id = ?"),
    GROUP_BY_ID("SELECT * FROM group WHERE group_id = ?"),
    STUDENT_BY_ID("SELECT * FROM student WHERE student_id = ?"),
    TEACHER_BY_ID("SELECT * FROM teacher WHERE teacher_id = ?"),
    DELETE_SUBJECT_BY_ID("DELETE FROM subject WHERE subject_id = ?"),
    DELETE_STUDENT_SUBJECT_BY_ID("DELETE FROM student_subject WHERE student_subject_id = ?"),
    DELETE_GROUP_BY_ID("DELETE FROM group WHERE group_id = ?"),
    DELETE_STUDENT_BY_ID("DELETE FROM student WHERE student_id = ?"),
    DELETE_TEACHER_BY_ID("DELETE FROM teacher WHERE teacher_id = ?"),
    ADD_SUBJECT("INSERT INTO subject VALUES (subject_id_seq.nextval, ?, ?, ?)"),
    ADD_STUDENT_SUBJECT("INSERT INTO student_subject VALUES (student_subject_id_seq.nextval, ?, ?, ?, ?)"),
    ADD_GROUP("INSERT INTO group VALUES (group_id_seq.nextval, ?)"),
    ADD_STUDENT("INSERT INTO student VALUES (student_id_seq.nextval, ?, ?, ?, ?, ?)"),
    ADD_TEACHER("INSERT INTO teacher VALUES (teacher_id_seq.nextval, ?, ?, ?)"),
    UPDATE_SUBJECT("UPDATE subject SET subject_name=?, max_grade=?, pass_proc_grade=? WHERE subject_id=?"),
    UPDATE_STUDENT_SUBJECT("UPDATE student_subject SET total_grade=?, student_id=?, subject_id=?, teacher_id=? WHERE student_subject_id=?"),
    UPDATE_GROUP("UPDATE group SET group_name=? WHERE group_id=?"),
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
