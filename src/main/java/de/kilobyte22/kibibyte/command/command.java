package de.kilobyte22.kibibyte.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// EWWWWW A JAVA FILE IN A SCALA PROJECT D:
@Retention(RetentionPolicy.RUNTIME)
public @interface command {
    String name() default "";
    // In kibi 8, now you specify command usage etc in your plugin metadata file
}
