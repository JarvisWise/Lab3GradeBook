package org.example.tools.strings;

public enum PageName {
    ERROR_PAGE("error-page"),
    LOGIN_PAGE("login"),
    MAIN_PAGE("main-page"),
    PROFILE_PAGE("profile"),
    GROUP_LIST_PAGE("group-list"),
    TASK_LIST_PAGE("task-list"),
    STUDENT_LIST_PAGE("student-list"),
    SUBJECT_LIST_PAGE("subject-list"),
    SHOW_GROUP_PAGE("show-group-page"),
    SHOW_STUDENT_PAGE("show-student-page"),
    ADD_EDIT_GROUP_PAGE("edd-edit-group-page"),
    ADD_EDIT_STUDENT_PAGE("edd-edit-student-page"),
    ADD_EDIT_SUBJECT_PAGE("edd-edit-subject-page");


    public final String pageName;

    private PageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }
}
