package app.service;

import app.validation.Validate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import java.util.Set;

/**
 * Service demonstrating @Any literal injection.
 * Demonstrates: @Any annotation - allows injection of all beans of a given type
 */
@ApplicationScoped
public class AnyValidationService {

    // @Any injection - injects all Validate<String> implementations
    @Inject
    @Any
    private Set<Validate<String>> allValidators;

    public void validateWithAll(String input) {
        System.out.println("=== Using @Any to inject all validators ===");
        if (allValidators != null) {
            for (Validate<String> validator : allValidators) {
                validator.printValidation();
                boolean result = validator.process(input);
                System.out.println("  Result: " + result);
            }
        }
        System.out.println("Total validators found: " + (allValidators != null ? allValidators.size() : 0));
    }

    public int getValidatorCount() {
        return allValidators != null ? allValidators.size() : 0;
    }
}