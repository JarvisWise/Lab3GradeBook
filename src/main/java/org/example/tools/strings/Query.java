package org.example.tools.strings;

public enum Query {
    STUDENT_BY_ID_PASSWORD("SELECT * FROM student WHERE student_id = ? AND password = ?"),
    TEACHER_BY_ID_PASSWORD("SELECT * FROM teacher WHERE teacher_id = ? AND password = ?");

    public final String query;

    private Query(String query) {
        this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
