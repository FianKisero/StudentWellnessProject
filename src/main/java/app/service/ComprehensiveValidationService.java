package app.service;

import app.validation.Validate;
import app.validation.ValidatorQualifier;
import app.validation.ValidatorQualifier.ValidationChoice;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;

/**
 * Comprehensive service demonstrating all CDI concepts:
 * - Field injection
 * - Constructor injection
 * - Setter method injection
 * - Multiple qualifiers
 * - Built-in qualifiers (@Named, @Default, @Any, @Alternatives)
 * - DataSource injection
 */
@ApplicationScoped
public class ComprehensiveValidationService {

    // 1. Field injection with custom qualifier
    @Inject
    @ValidatorQualifier(ValidationChoice.MOOD)
    private Validate<String> moodValidator;

    // 2. Field injection with multiple qualifiers
    @Inject
    @ValidatorQualifier(ValidationChoice.USER)
    private Validate<String> userValidator;

    // 3. DataSource injection - demonstrates injecting datasource
    @Inject
    private DataSource dataSource;

    // 4. Setter method injection
    private Validate<String> tipsValidator;

    // 5. @Any injection - inject all validators
    @Inject
    @jakarta.enterprise.inject.Any
    private java.util.Set<Validate<String>> allValidators;

    // Setter injection
    @Inject
    public void setTipsValidator(
            @ValidatorQualifier(ValidationChoice.TIPS) Validate<String> tipsValidator) {
        this.tipsValidator = tipsValidator;
    }

    /**
     * Validate mood using field-injected validator
     */
    public void validateMood(String mood) {
        System.out.println("=== Field Injection: Validating Mood ===");
        if (moodValidator != null) {
            moodValidator.printValidation();
            boolean result = moodValidator.process(mood);
            System.out.println("Result: " + result);
        }
    }

    /**
     * Validate tips using setter-injected validator
     */
    public void validateTips(String tip) {
        System.out.println("=== Setter Injection: Validating Tips ===");
        if (tipsValidator != null) {
            tipsValidator.printValidation();
            boolean result = tipsValidator.process(tip);
            System.out.println("Result: " + result);
        }
    }

    /**
     * Validate user using field-injected validator
     */
    public void validateUser(String user) {
        System.out.println("=== Multiple Field Injections: Validating User ===");
        if (userValidator != null) {
            userValidator.printValidation();
            boolean result = userValidator.process(user);
            System.out.println("Result: " + result);
        }
    }

    /**
     * Demonstrate DataSource injection
     */
    public void testDataSource() {
        System.out.println("=== DataSource Injection ===");
        if (dataSource != null) {
            System.out.println("DataSource injected successfully: " + dataSource.getClass().getName());
        } else {
            System.out.println("DataSource is null");
        }
    }

    /**
     * Demonstrate @Any injection - get all validators
     */
    public void validateWithAll(String input) {
        System.out.println("=== @Any Injection: All Validators ===");
        if (allValidators != null) {
            System.out.println("Found " + allValidators.size() + " validators:");
            for (Validate<String> validator : allValidators) {
                validator.printValidation();
                validator.process(input);
            }
        }
    }

    /**
     * Run all validations
     */
    public void runAllValidations(String mood, String tip, String user) {
        System.out.println("\n========== COMPREHENSIVE VALIDATION SERVICE ==========");
        validateMood(mood);
        System.out.println();
        validateTips(tip);
        System.out.println();
        validateUser(user);
        System.out.println();
        testDataSource();
        System.out.println();
        validateWithAll("test input");
        System.out.println("========== END ==========\n");
    }
}