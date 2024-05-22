package pl.pja.s28201.tpo_10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pja.s28201.tpo_10.model.UrlObject;

@Service
public class UrlParsingService {

    private final IdAndUrlService idAndUrlService;

    @Autowired
    public UrlParsingService(IdAndUrlService idAndUrlService) {
        this.idAndUrlService = idAndUrlService;
    }

    public UrlObject createProtectedUrlObject(String name, String targetUrl, String password) {
        var urlObject = new UrlObject();
        urlObject.setId(idAndUrlService.generateId());
        urlObject.setName(name);
        urlObject.setTargetUrl(targetUrl);
        urlObject.setPassword(password);
        urlObject.setRedirectUrl(idAndUrlService.getDefaultUrlPath());

        return urlObject;
    }
}
