package pl.pja.s28201.tpo_10.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private int lowerMin;
    private int upperMin;
    private int numbersMin;
    private int specialMin;

    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern DIGIT = Pattern.compile("\\d");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[^a-zA-Z0-9]");

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        this.lowerMin = constraintAnnotation.lowerMin();
        this.upperMin = constraintAnnotation.upperMin();
        this.numbersMin = constraintAnnotation.numbersMin();
        this.specialMin = constraintAnnotation.specialMin();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        long lowerCount = password.chars().filter(ch -> LOWER_CASE.matcher(Character.toString(ch)).matches()).count();
        long upperCount = password.chars().filter(ch -> UPPER_CASE.matcher(Character.toString(ch)).matches()).count();
        long numberCount = password.chars().filter(ch -> DIGIT.matcher(Character.toString(ch)).matches()).count();
        long specialCount = password.chars().filter(ch -> SPECIAL_CHAR.matcher(Character.toString(ch)).matches()).count();

        boolean isValid = true;
        if (lowerCount < lowerMin) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password must contain at least " + lowerMin + " lowercase letter(s)")
                    .addConstraintViolation();
        }
        if (upperCount < upperMin) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password must contain at least " + upperMin + " uppercase letter(s)")
                    .addConstraintViolation();
        }
        if (numberCount < numbersMin) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password must contain at least " + numbersMin + " number(s)")
                    .addConstraintViolation();
        }
        if (specialCount < specialMin) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password must contain at least " + specialMin + " special character(s)")
                    .addConstraintViolation();
        }
        if (password.isEmpty()) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password must not be empty.")
                    .addConstraintViolation();
        }
        if (password.length() < 10) {
            isValid = false;
            context.buildConstraintViolationWithTemplate("Password length must be more or equal to 10.")
                    .addConstraintViolation();
        }

        context.disableDefaultConstraintViolation();

        return isValid;
    }
}
