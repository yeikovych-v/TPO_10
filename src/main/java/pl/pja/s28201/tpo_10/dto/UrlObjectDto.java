package pl.pja.s28201.tpo_10.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import pl.pja.s28201.tpo_10.validation.password.StrongPassword;
import pl.pja.s28201.tpo_10.validation.url.HttpsUrl;

public class UrlObjectDto {
    @Length(min = 5, max = 20)
    private String name;

    @StrongPassword(lowerMin = 1, upperMin = 2, numbersMin = 3, specialMin = 4)
    private String password;
    @URL
    @HttpsUrl
    @NotEmpty
    private String targetUrl;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public String toString() {
        return "UrlObjectDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                '}';
    }
}
