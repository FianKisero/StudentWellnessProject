package app.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * User validation implementation.
 * Demonstrates: Constructor injection with multiple qualifiers
 */
@ValidatorQualifier(ValidatorQualifier.ValidationChoice.USER)
@ApplicationScoped
public class ValidateUser implements Validate<String> {

    private int validationCounter = 0;
    
    // Additional injected validators - demonstrates multiple qualifiers
    private final Validate<String> moodValidator;
    private final Validate<String> tipsValidator;

    // Constructor injection - demonstrates constructor injection point
    @Inject
    public ValidateUser(
            @ValidatorQualifier(ValidatorQualifier.ValidationChoice.MOOD) Validate<String> moodValidator,
            @ValidatorQualifier(ValidatorQualifier.ValidationChoice.TIPS) Validate<String> tipsValidator) {
        this.moodValidator = moodValidator;
        this.tipsValidator = tipsValidator;
    }

    @Override
    public void printValidation() {
        System.out.println("Validate User");
    }

    public boolean process(String user) {
        validationCounter++;
        System.out.println("Validating User: " + user + " - Validation #" + validationCounter);
        
        // Use injected validators
        if (moodValidator != null) {
            moodValidator.process("default");
        }
        if (tipsValidator != null) {
            tipsValidator.process("default");
        }
        
        return user != null && !user.isEmpty();
    }
}