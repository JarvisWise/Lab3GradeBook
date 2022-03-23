package org.example.tools.strings;

public enum Role {
    STUDENT("student"),
    TEACHER("teacher"),
    ADMIN("admin");//--

    public final String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
