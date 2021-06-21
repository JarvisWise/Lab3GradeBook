package org.example.tools.custom.exceptions;

public class EntityParseException extends Exception {
    public EntityParseException(String errorMessage) {
        super(errorMessage);
    }

    public EntityParseException(Throwable err) {
        super(err);
    }

    public EntityParseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
