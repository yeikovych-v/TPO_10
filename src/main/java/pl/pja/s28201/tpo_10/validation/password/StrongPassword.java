package pl.pja.s28201.tpo_10.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface StrongPassword {

    String message() default "Password is not strong enough.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int lowerMin() default 1;
    int upperMin() default 1;
    int numbersMin() default 1;
    int specialMin() default 1;
}
