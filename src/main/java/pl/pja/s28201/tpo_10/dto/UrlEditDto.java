package pl.pja.s28201.tpo_10.dto;

import jakarta.persistence.Id;

public class UrlEditDto {

    @Id
    private String id;
    private String name;
    private String targetUrl;
    private String redirectUrl;
    private long visits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "UrlEditDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", visits=" + visits +
                '}';
    }
}
