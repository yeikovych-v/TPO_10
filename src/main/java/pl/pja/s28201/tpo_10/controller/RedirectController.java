package pl.pja.s28201.tpo_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pja.s28201.tpo_10.repository.UrlObjectRepository;

@RestController
@RequestMapping("/api/red")
public class RedirectController {

    private final UrlObjectRepository urlRepository;

    @Autowired
    public RedirectController(UrlObjectRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> redirectIfPresent(@PathVariable String id) {
        var dbOpt = urlRepository.findById(id);

        if (dbOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found");
        }

        var toVisit = dbOpt.get();
        toVisit.incrementVisits();

        urlRepository.save(toVisit);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, toVisit.getTargetUrl())
                .body("302 Found " + toVisit.getTargetUrl());
    }
}
