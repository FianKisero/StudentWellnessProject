package app.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * General validation implementation.
 * Demonstrates: @Default annotation - used when no specific qualifier is specified
 */
@Named("validateGeneral")
@ApplicationScoped
public class ValidateGeneral implements Validate<String> {

    private int validationCounter = 0;

    @Override
    public void printValidation() {
        System.out.println("Validate General (Default)");
    }

    public boolean process(String input) {
        validationCounter++;
        System.out.println("Validating General Input: " + input + " - Validation #" + validationCounter);
        return input != null;
    }
}