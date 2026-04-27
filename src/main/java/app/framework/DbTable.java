package app.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to mark a class as a database table.
 * Demonstrates: Custom annotation for ORM-like mapping
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTable {
    String name();
}