package de.kilobyte22.kibibyte.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface needsPermission {
    public String value();
}
