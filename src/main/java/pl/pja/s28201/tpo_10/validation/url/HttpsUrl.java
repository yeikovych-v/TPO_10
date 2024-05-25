package pl.pja.s28201.tpo_10.validation.url;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = HttpsUrlValidator.class)
public @interface HttpsUrl {
    String message() default "{s28201.errors.url.not-https.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
