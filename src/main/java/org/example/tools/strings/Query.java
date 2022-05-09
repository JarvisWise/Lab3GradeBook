package org.example.tools.strings;

public enum Query {
    ALL_STUDENTS("SELECT * FROM student"),
    ALL_SUBJECTS("SELECT * FROM subject"),
    ALL_GROUPS("SELECT * FROM group_"),
    STUDENTS_BY_GROUP_ID("SELECT * FROM student WHERE group_id = ?"),
    STUDENT_BY_LOGIN_NAME_AND_PASSWORD("SELECT * FROM student WHERE login_name = ? AND password = ?"),
    TEACHER_BY_LOGIN_NAME_AND_PASSWORD("SELECT * FROM teacher WHERE login_name = ? AND password = ?"),
    SUBJECT_BY_ID("SELECT * FROM subject WHERE subject_id = ?"),
    SUBJECTS_BY_STUDENT_ID("SELECT * FROM student_subject WHERE student_id = ?"),
    STUDENTS_BY_SUBJECT_ID("SELECT * FROM student WHERE student_id IN (" +
            "SELECT * FROM student_subject WHERE subject_id = ?)"),
    /*SELECT st.* FROM student st
    JOIN subject sub ON (st.student_id = sub.student_id)
    WHERE sub.subject_id = ?*/
    STUDENT_SUBJECT_BY_ID("SELECT * FROM student_subject WHERE student_subject_id = ?"),
    GROUP_BY_ID("SELECT * FROM group_ WHERE group_id = ?"),
    STUDENT_BY_ID("SELECT * FROM student WHERE student_id = ?"),
    TEACHER_BY_ID("SELECT * FROM teacher WHERE teacher_id = ?"),
    TASK_BY_ID("SELECT * FROM task WHERE task_id = ?"),
    STUDENT_TASK_BY_ID("SELECT * FROM student_task WHERE student_subject_id = ? AND task_id = ?"), //check for this one
    DELETE_SUBJECT_BY_ID("DELETE FROM subject WHERE subject_id = ?"),
    DELETE_STUDENT_SUBJECT_BY_ID("DELETE FROM student_subject WHERE student_subject_id = ?"),
    DELETE_GROUP_BY_ID("DELETE FROM group_ WHERE group_id = ?"),
    DELETE_STUDENT_BY_ID("DELETE FROM student WHERE student_id = ?"),
    DELETE_TEACHER_BY_ID("DELETE FROM teacher WHERE teacher_id = ?"),
    DELETE_TASK_BY_ID("DELETE FROM task WHERE task_id = ?"),
    DELETE_STUDENT_TASK_BY_ID("DELETE FROM student_task WHERE student_subject_id = ? AND task_id = ?"),
    ADD_SUBJECT("INSERT INTO subject VALUES (subject_id_seq.nextval, ?, ?, ?)"),
    ADD_STUDENT_SUBJECT("INSERT INTO student_subject VALUES (student_subject_id_seq.nextval, ?, ?, ?, ?)"),
    ADD_GROUP("INSERT INTO group_ VALUES (group_id_seq.nextval, ?)"),
    ADD_STUDENT("INSERT INTO student VALUES (student_id_seq.nextval, ?, ?, ?, ?, ?)"),
    ADD_TEACHER("INSERT INTO teacher VALUES (teacher_id_seq.nextval, ?, ?, ?)"),
    ADD_TASK("INSERT INTO task VALUES (task_id_seq.nextval, ?, ?, ?)"),
    ADD_STUDENT_TASK("INSERT INTO student_task VALUES (?, ?, ?, ?)"),
    UPDATE_SUBJECT("UPDATE subject SET subject_name=?, max_grade=?, pass_proc_grade=? WHERE subject_id=?"),
    UPDATE_STUDENT_SUBJECT("UPDATE student_subject SET total_grade=?, student_id=?, subject_id=?, teacher_id=? WHERE student_subject_id=?"),
    UPDATE_GROUP("UPDATE group_ SET group_name=? WHERE group_id=?"),
    UPDATE_STUDENT("UPDATE student SET first_name=?, last_name=?, headman=?, group_id=?, WHERE student_id=?"),
    UPDATE_TEACHER("UPDATE teacher SET first_name=?, last_name=? WHERE teacher_id=?"),
    //--do more specific query for update
    UPDATE_TASK_NAME("UPDATE task SET task_name=? WHERE task_id=?"),
    UPDATE_TASK_MAX_GRADE("UPDATE task SET max_grade=? WHERE task_id=?"),
    UPDATE_STUDENT_TASK_GRADE("UPDATE student_task SET grade=? WHERE student_subject_id = ? AND task_id = ?"),
    CHANGE_STUDENT_PASSWORD("UPDATE student SET password=? WHERE student_id=?"),
    CHANGE_TEACHER_PASSWORD("UPDATE teacher SET password=? WHERE teacher_id=?"),
    STUDENT_SUBJECT_LIST_BY_STUDENT_ID("SELECT * FROM student_subject WHERE student_id = ?"),
    STUDENT_SUBJECT_LIST_BY_SUBJECT_ID("SELECT * FROM student_subject WHERE subject_id = ?"),
    TEACHER_LIST_BY_SUBJECT_ID("SELECT t.* FROM teacher_subject ts " +
            "JOIN teacher t ON (t.teacher_id = ts.teacher_id) WHERE ts.subject_id = ?"),
    SUBJECT_LIST_BY_TEACHER_ID("SELECT s.* FROM teacher_subject ts " +
            "JOIN subject s ON (s.subject_id = ts.subject_id) WHERE ts.teacher_id = ?"),
    STUDENT_SUBJECT_MAP_BY_SUBJECT_ID("SELECT s.*, ss.* FROM student_subject ss " +
            "JOIN student s ON (ss.student_id = s.student_id) WHERE ss.subject_id = ?"),
    STUDENT_SUBJECT_MAP_BY_STUDENT_ID("SELECT s.*, ss.* FROM student_subject ss " +
            "JOIN subject s ON (ss.subject_id = s.subject_id) WHERE ss.student_id = ?"),
    /*SEARCH_BY_STUDENT_FULL_NAME("SELECT * FROM student WHERE REGEXP_LIKE (first_name||' '||last_name, '%?%', 'i')" +
            "OR REGEXP_LIKE (last_name||' '||first_name, '%?%', 'i')"),*/
    /*SEARCH_BY_STUDENT_FULL_NAME("SELECT * FROM student WHERE first_name||' '||last_name ILIKE '%?%' " +
            "OR last_name||' '||first_name ILIKE '%?%'"),*/
    //SEARCH_BY_GROUP_NAME("SELECT * FROM group WHERE REGEXP_LIKE (group_name, '%?%', 'i')"),
    //SEARCH_BY_SUBJECT_NAME("SELECT * FROM subject WHERE REGEXP_LIKE (subject_name, '%?%', 'i')")
    SEARCH_BY_STUDENT_FULL_NAME("SELECT * FROM student WHERE lower(first_name||' '||last_name) LIKE '%'||lower(?)||'%'"),
    SEARCH_BY_GROUP_NAME("SELECT * FROM group_ WHERE lower(group_name) LIKE '%'||lower(?)||'%'"),
    SEARCH_BY_SUBJECT_NAME("SELECT * FROM subject WHERE lower(subject_name) LIKE '%'||lower(?)||'%'");

    public final String query;

    private Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
