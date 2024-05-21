package pl.pja.s28201.tpo_10.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import pl.pja.s28201.tpo_10.exception.IdNotDefinedException;

@Entity
public class UrlObject {

//    @Id
//    @JsonIgnore
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Id
    private String id;
    private String name;
    @JsonIgnore
    private String password = "";
    private String targetUrl;
    private String redirectUrl;
    private long visits;

    public String getId() {
        return id;
    }

    public void setId(String idStr) {
        this.id = idStr;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String defaultPath) {
        if (id.length() != 10) throw new IdNotDefinedException("Id is not defined. Cannot set redirection Url.");
        this.redirectUrl = defaultPath + id;
    }
}
