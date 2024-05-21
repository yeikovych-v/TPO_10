package pl.pja.s28201.tpo_10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28201.tpo_10.dto.ProtectedUrlDto;
import pl.pja.s28201.tpo_10.dto.UrlObjectDto;
import pl.pja.s28201.tpo_10.model.UrlObject;
import pl.pja.s28201.tpo_10.repository.UrlObjectRepository;
import pl.pja.s28201.tpo_10.service.UrlParsingService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/links", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    private final UrlParsingService urlParsingService;
    private final UrlObjectRepository urlRepository;

    @Autowired
    public ApiController(UrlParsingService urlParsingService, UrlObjectRepository urlRepository) {
        this.urlParsingService = urlParsingService;
        this.urlRepository = urlRepository;
    }

    @PostMapping("/no-psw")
    public ResponseEntity<UrlObject> postNewLinkNoPsw(@RequestBody UrlObjectDto urlDto) {
        var urlObject = urlParsingService.createUrlObjectWithNoPass(urlDto.getName(), urlDto.getTargetUrl());
        System.out.println("PSW::" + urlObject.getPassword());

        urlRepository.save(urlObject);

        return ResponseEntity.status(HttpStatus.CREATED).body(urlObject);
    }

    @PostMapping("/psw")
    public ResponseEntity<UrlObject> postNewLink(@RequestBody ProtectedUrlDto protectedUrlDto) {
        var urlObject = urlParsingService.createProtectedUrlObject(protectedUrlDto.getName(), protectedUrlDto.getTargetUrl(), protectedUrlDto.getPassword());
        System.out.println("PSW::" + urlObject.getPassword());

        urlRepository.save(urlObject);

        return ResponseEntity.status(HttpStatus.CREATED).body(urlObject);
    }

    @GetMapping
    public ResponseEntity<List<UrlObject>> getAllLinks() {
        return ResponseEntity.status(HttpStatus.OK).body(urlRepository.findAll());
    }

//    @PostMapping
//    public ResponseEntity<?> postNewLinkProtected(ProtectedUrlDto protectedUrlDto) {
//
//        return ResponseEntity.status(HttpStatus.OK).body(protectedUrlDto);
//    }
}
