package com.victoreis.urlshortener.controller;

import java.net.URI;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.victoreis.urlshortener.entity.Url;
import com.victoreis.urlshortener.service.UrlService;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        Url shortUrl = urlService.findByShortCode(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(shortUrl.getOriginalUrl()));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
