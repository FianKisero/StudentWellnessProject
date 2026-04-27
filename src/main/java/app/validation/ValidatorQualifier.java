package app.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.inject.Qualifier;

/**
 * Custom qualifier annotation for validation types.
 * Demonstrates: @Qualifier - custom qualifier annotation
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
public @interface ValidatorQualifier {

    ValidationChoice value();

    enum ValidationChoice {
        MOOD,
        TIPS,
        USER,
        SESSION,
        GENERAL
    }
}