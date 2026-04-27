package app.validation;

/**
 * Generic validation interface.
 * Demonstrates: Generic interface pattern
 */
public interface Validate<T> {

    void printValidation();

    boolean process(T model);
}