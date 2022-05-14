package org.example.tools.strings;

public enum Strings {
    NOT_YET("NotYet");

    public final String strings;

    private Strings(String strings) {
        this.strings = strings;
    }

    public String getStrings() {
        return strings;
    }
}
