package app.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Mood validation implementation.
 * Demonstrates: Field injection with custom qualifier
 */
@ValidatorQualifier(ValidatorQualifier.ValidationChoice.MOOD)
@ApplicationScoped
public class ValidateMood implements Validate<String> {

    // Field injection - demonstrates field injection point
    // Use @Default since ValidateGeneral doesn't have @ValidatorQualifier
    @Inject
    @jakarta.enterprise.inject.Default
    private Validate<String> generalValidator;

    private int validationCounter = 0;

    @Override
    public void printValidation() {
        System.out.println("Validate Mood");
    }

    public boolean process(String mood) {
        validationCounter++;
        System.out.println("Validating Mood: " + mood + " - Validation #" + validationCounter);
        
        // Use injected general validator
        if (generalValidator != null) {
            generalValidator.printValidation();
        }
        
        return mood != null && !mood.isEmpty();
    }
}