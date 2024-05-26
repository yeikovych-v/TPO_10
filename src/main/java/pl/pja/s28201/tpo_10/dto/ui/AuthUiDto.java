package pl.pja.s28201.tpo_10.dto.ui;

public class AuthUiDto {

    private String authHeader;
    private String urlLabel;
    private String urlPlaceHolder;
    private String passwordLabel;
    private String passwordPlaceHolder;
    private String buttonSubmit;
    private String buttonCancel;

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    public String getUrlLabel() {
        return urlLabel;
    }

    public void setUrlLabel(String urlLabel) {
        this.urlLabel = urlLabel;
    }

    public String getUrlPlaceHolder() {
        return urlPlaceHolder;
    }

    public void setUrlPlaceHolder(String urlPlaceHolder) {
        this.urlPlaceHolder = urlPlaceHolder;
    }

    public String getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(String passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public String getPasswordPlaceHolder() {
        return passwordPlaceHolder;
    }

    public void setPasswordPlaceHolder(String passwordPlaceHolder) {
        this.passwordPlaceHolder = passwordPlaceHolder;
    }

    public String getButtonSubmit() {
        return buttonSubmit;
    }

    public void setButtonSubmit(String buttonSubmit) {
        this.buttonSubmit = buttonSubmit;
    }

    public String getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(String buttonCancel) {
        this.buttonCancel = buttonCancel;
    }
}
