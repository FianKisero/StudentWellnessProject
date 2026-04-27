package app.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;

/**
 * Alternative mood validation with stricter rules.
 * Demonstrates: @Alternative annotation - allows switching implementations at deployment
 */
@Alternative
@ValidatorQualifier(ValidatorQualifier.ValidationChoice.MOOD)
@ApplicationScoped
public class ValidateMoodAlternative implements Validate<String> {

    // Field injection
    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.USER)
    private Validate<String> userValidator;

    private int validationCounter = 0;

    @Override
    public void printValidation() {
        System.out.println("Validate Mood (Alternative - Strict)");
    }

    public boolean process(String mood) {
        validationCounter++;
        System.out.println("Strictly Validating Mood: " + mood + " - Validation #" + validationCounter);
        
        // Use injected validator
        if (userValidator != null) {
            userValidator.process("checked");
        }
        
        // Stricter validation rules
        return mood != null && 
               !mood.isEmpty() && 
               mood.length() >= 3 &&
               (mood.equalsIgnoreCase("happy") || 
                mood.equalsIgnoreCase("sad") || 
                mood.equalsIgnoreCase("neutral") ||
                mood.equalsIgnoreCase("excited") ||
                mood.equalsIgnoreCase("tired"));
    }
}