package org.example.entities;

public abstract class User {
    public abstract String getLoginName();
    public abstract String getFirstName();
    public abstract String getLastName();
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    public String getFullNameWithLogin() {
        return getFirstName() +
                " " + getLastName() +
                " (" + getLoginName() + ")";
    }
    public abstract String getUserId();
    public abstract String getRole();
}
