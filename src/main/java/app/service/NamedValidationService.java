package app.service;

import app.validation.Validate;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Named validation service demonstrating @Named on constructor and setter methods.
 * Demonstrates: @Named annotation on beans
 */
@ApplicationScoped
@Named("namedValidationService")
public class NamedValidationService {

    private Validate<String> defaultValidator;

    // Constructor injection with @Named
    @Inject
    public NamedValidationService(
            @Named("validateGeneral") Validate<String> defaultValidator) {
        this.defaultValidator = defaultValidator;
    }

    public void validateWithDefault(String input) {
        if (defaultValidator != null) {
            defaultValidator.printValidation();
            boolean result = defaultValidator.process(input);
            System.out.println("Default validation result: " + result);
        }
    }

    public String getServiceName() {
        return "NamedValidationService";
    }
}