package app.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to mark a field as a database column.
 * Demonstrates: Custom annotation for field mapping
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DbColumn {
    String name();
    boolean primaryKey() default false;
    boolean autoIncrement() default false;
    boolean nullable() default true;
}