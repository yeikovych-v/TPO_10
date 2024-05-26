package pl.pja.s28201.tpo_10.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern DIGIT = Pattern.compile("\\d");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[^a-zA-Z0-9]");

    private final MessageSource messageSource;
    private int lowerMin;
    private int upperMin;
    private int numbersMin;
    private int specialMin;
    private int lengthMin;

    @Autowired
    public PasswordValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
        this.lowerMin = constraintAnnotation.lowerMin();
        this.upperMin = constraintAnnotation.upperMin();
        this.numbersMin = constraintAnnotation.numbersMin();
        this.specialMin = constraintAnnotation.specialMin();
        this.lengthMin = constraintAnnotation.lengthMin();
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
        if (password.isEmpty()) {
            return true;
        }
        if (lowerCount < lowerMin) {
            isValid = false;
            String msg = resolveMessage("{s28201.errors.password.min-lc.message}", lowerMin);
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }
        if (upperCount < upperMin) {
            isValid = false;
            String msg = resolveMessage("{s28201.errors.password.min-uc.message}", upperMin);
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }
        if (numberCount < numbersMin) {
            isValid = false;
            String msg = resolveMessage("{s28201.errors.password.min-numbers.message}", numbersMin);
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }
        if (specialCount < specialMin) {
            isValid = false;
            String msg = resolveMessage("{s28201.errors.password.min-special.message}", specialMin);
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }
        if (password.length() < lengthMin) {
            isValid = false;
            String msg = resolveMessage("{s28201.errors.password.min-length.message}", lengthMin);
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }

        context.disableDefaultConstraintViolation();

        return isValid;
    }

    private String resolveMessage(String code, Object... args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, locale);
    }
}
