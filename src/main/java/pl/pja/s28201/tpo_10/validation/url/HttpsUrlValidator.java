package pl.pja.s28201.tpo_10.validation.url;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HttpsUrlValidator implements ConstraintValidator<HttpsUrl, String> {


    @Override
    public void initialize(HttpsUrl annotation) {
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        return validateHttpsUrl(url);
    }

    private boolean validateHttpsUrl(String url) {
        return url.startsWith("https://");
    }

}
