package app.validation;

import app.model.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * Student validation implementation.
 * Demonstrates: Field injection with custom qualifier
 */
@ValidatorQualifier(ValidatorQualifier.ValidationChoice.USER)
@ApplicationScoped
public class ValidateStudent implements Validate<Student> {

    // Field injection - demonstrates field injection point
    @Inject
    @ValidatorQualifier(ValidatorQualifier.ValidationChoice.MOOD)
    private Validate<String> moodValidator;

    private int validationCounter = 0;

    @Override
    public void printValidation() {
        System.out.println("Validate Student");
    }

    public boolean process(Student student) {
        validationCounter++;
        System.out.println("Validating Student: " + student.getName() + " - Validation #" + validationCounter);
        
        // Use injected mood validator
        if (moodValidator != null) {
            moodValidator.printValidation();
        }
        
        return student != null && 
               student.getName() != null && 
               !student.getName().isEmpty() &&
               student.getEmail() != null &&
               student.getEmail().contains("@");
    }
}