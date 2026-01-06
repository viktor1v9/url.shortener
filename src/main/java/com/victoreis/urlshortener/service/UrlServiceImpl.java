package com.victoreis.urlshortener.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.victoreis.urlshortener.entity.Url;
import com.victoreis.urlshortener.exception.ResourceNotFoundException;
import com.victoreis.urlshortener.repository.UrlRepository;
import com.victoreis.urlshortener.util.ShortCodeGenerator;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;

    public UrlServiceImpl(UrlRepository repository) {
        this.repository = repository;
    }

    @Override
    public Url createShortUrl(String originalUrl) {
        String shortCode;

        do {
            shortCode = ShortCodeGenerator.generate(6);
        } while(repository.existsByShortCode(shortCode));

        Url shortUrl = new Url();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setShortCode(shortCode);
        shortUrl.setAccessCount(0);
        shortUrl.setCreatedAt(LocalDateTime.now());
        shortUrl.setExpiresAt(Instant.now().plus(7, ChronoUnit.DAYS));

        return repository.save(shortUrl);
    }

    @Override
    public Url findByShortCode(String shortCode) {
        Url shortUrl = repository.findByShortCode(shortCode)
        .orElseThrow(()-> new ResourceNotFoundException("URL não encontrada"));

        if (shortUrl.getExpiresAt() != null && shortUrl.getExpiresAt().isBefore(Instant.now())) {
            throw new ResourceNotFoundException("URL expirou");
        }
        
        shortUrl.setAccessCount(shortUrl.getAccessCount() + 1);
        repository.save(shortUrl);

        
        return shortUrl;
    }

}
