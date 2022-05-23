package org.example.entities;

public abstract class User {
    public abstract String getEmail();
    public abstract String getFirstName();
    public abstract String getLastName();
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    public String getFullNameWithEmail() {
        return getFirstName() +
                " " + getLastName() +
                " (" + getEmail() + ")";
    }
    public abstract String getUserId();
    public abstract String getRole();
}
