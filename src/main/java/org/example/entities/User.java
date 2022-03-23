package org.example.entities;

public abstract class User {
    public abstract String getFirstName();
    public abstract String getLastName();
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    public abstract String getUserId();
    public abstract String getRole();
}
