package app.service;

import app.validation.Validate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Validation service demonstrating setter method injection.
 * Demonstrates: Setter injection point with @Inject
 */
@ApplicationScoped
public class ValidationService {

    // Field injection
    @Inject
    @app.validation.ValidatorQualifier(app.validation.ValidatorQualifier.ValidationChoice.MOOD)
    private Validate<String> moodValidator;

    // Setter method injection - demonstrates setter method injection point
    private Validate<String> tipsValidator;
    private Validate<String> userValidator;

    @Inject
    public void setTipsValidator(
            @app.validation.ValidatorQualifier(app.validation.ValidatorQualifier.ValidationChoice.TIPS) 
            Validate<String> tipsValidator) {
        this.tipsValidator = tipsValidator;
    }

    @Inject
    public void setUserValidator(
            @app.validation.ValidatorQualifier(app.validation.ValidatorQualifier.ValidationChoice.USER) 
            Validate<String> userValidator) {
        this.userValidator = userValidator;
    }

    public void validateMood(String mood) {
        if (moodValidator != null) {
            moodValidator.printValidation();
            boolean result = moodValidator.process(mood);
            System.out.println("Mood validation result: " + result);
        }
    }

    public void validateTips(String tip) {
        if (tipsValidator != null) {
            tipsValidator.printValidation();
            boolean result = tipsValidator.process(tip);
            System.out.println("Tips validation result: " + result);
        }
    }

    public void validateUser(String user) {
        if (userValidator != null) {
            userValidator.printValidation();
            boolean result = userValidator.process(user);
            System.out.println("User validation result: " + result);
        }
    }

    public void validateAll(String mood, String tip, String user) {
        validateMood(mood);
        validateTips(tip);
        validateUser(user);
    }
}