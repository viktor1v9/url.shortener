package com.victoreis.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import com.victoreis.urlshortener.service.UrlService;
import com.victoreis.urlshortener.dto.CreateUrlResponse;
import com.victoreis.urlshortener.dto.CreateUrlRequest;
import com.victoreis.urlshortener.entity.Url;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {   
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<CreateUrlResponse> create(
        @Valid @RequestBody CreateUrlRequest request) {

            Url shortUrl = urlService.createShortUrl(request.getOriginalUrl());

            CreateUrlResponse response = new CreateUrlResponse(
                    shortUrl.getShortCode(),
                    shortUrl.getOriginalUrl()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    
}
