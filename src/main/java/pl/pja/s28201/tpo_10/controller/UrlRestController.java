package pl.pja.s28201.tpo_10.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28201.tpo_10.dto.UrlObjectDto;
import pl.pja.s28201.tpo_10.model.UrlObject;
import pl.pja.s28201.tpo_10.repository.UrlObjectRepository;
import pl.pja.s28201.tpo_10.service.UrlParsingService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/links", produces = MediaType.APPLICATION_JSON_VALUE)
public class UrlRestController {

    private final UrlParsingService urlParsingService;
    private final UrlObjectRepository urlRepository;

    @Autowired
    public UrlRestController(UrlParsingService urlParsingService, UrlObjectRepository urlRepository) {
        this.urlParsingService = urlParsingService;
        this.urlRepository = urlRepository;
    }

    @PostMapping
    public ResponseEntity<Object> postNewLink(@RequestBody @Valid UrlObjectDto urlObjectDto, Errors errors) {
        var psw = urlObjectDto.getPassword();
        if (psw == null) psw = "";

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        var urlByName = urlRepository.findByTargetUrl(urlObjectDto.getTargetUrl());

        if (urlByName.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request - Provided Url already exists in the system.");
        }

        var urlObject = urlParsingService.createProtectedUrlObject(urlObjectDto.getName(), urlObjectDto.getTargetUrl(), psw);
        System.out.println("PSW::" + urlObject.getPassword());

        urlRepository.save(urlObject);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("location", urlObject.getRedirectUrl())
                .body(urlObject);
    }

    @GetMapping
    public ResponseEntity<List<UrlObject>> getAllUrls() {
        return ResponseEntity.status(HttpStatus.OK).body(urlRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUrlById(@PathVariable String id) {
        var urlOpt = urlRepository.findById(id);

        if (urlOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found");

        return ResponseEntity.status(HttpStatus.OK).body(urlOpt.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable String id, @RequestBody UrlObjectDto urlDto) {
        var dbOpt = urlRepository.findById(id);

        if (dbOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found");
        }

        var toUpdate = dbOpt.get();

        if (hasWrongPassword(toUpdate, urlDto.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .header("Reason", "wrong password")
                    .body("403 Forbidden");
        }

        var urlByName = urlRepository.findByTargetUrl(urlDto.getTargetUrl());

        if (urlByName.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .header("Reason", "Url with this target name already exists.")
                    .body("409 Conflict");
        }

        var name = urlDto.getName();
        if (name != null) {
            toUpdate.setName(name);
        }

        var targetUrl = urlDto.getTargetUrl();
        if (targetUrl != null) {
            toUpdate.setTargetUrl(targetUrl);
        }

        urlRepository.save(toUpdate);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 No Content");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id, @RequestHeader String pass) {
        var dbOpt = urlRepository.findById(id);

        if (dbOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 No Content");
        }

        var toDelete = dbOpt.get();
        if (hasWrongPassword(toDelete, pass)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .header("Reason", "wrong password")
                    .body("403 Forbidden");
        }

        urlRepository.delete(toDelete);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("204 No Content");
    }

    private boolean hasWrongPassword(UrlObject modified, String dtoPassword) {
        return modified.getPassword() == null || modified.getPassword().isEmpty() || !modified.getPassword().equals(dtoPassword);
    }

}
