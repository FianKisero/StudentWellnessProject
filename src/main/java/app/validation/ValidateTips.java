package app.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * Tips validation implementation.
 * Demonstrates: @Named annotation usage
 */
@Named("validateTips")
@ValidatorQualifier(ValidatorQualifier.ValidationChoice.TIPS)
@ApplicationScoped
public class ValidateTips implements Validate<String> {

    private int validationCounter = 0;

    @Override
    public void printValidation() {
        System.out.println("Validate Tips");
    }

    public boolean process(String tip) {
        validationCounter++;
        System.out.println("Validating Tips: " + tip + " - Validation #" + validationCounter);
        return tip != null && tip.length() > 5;
    }
}