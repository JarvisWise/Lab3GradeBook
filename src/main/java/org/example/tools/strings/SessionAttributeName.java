package org.example.tools.strings;

public enum SessionAttributeName {
    CURRENT_USERNAME("current_username"),
    CURRENT_FULL_NAME("current_full_name"),//--
    CURRENT_USER_ID("current_user_id"),
    CURRENT_USER_ROLE("current_user_role");//--

    public final String sessionAttributeName;

    private SessionAttributeName(String sessionAttributeName) {
        this.sessionAttributeName = sessionAttributeName;
    }

    public String getSessionAttributeName() {
        return sessionAttributeName;
    }
}
