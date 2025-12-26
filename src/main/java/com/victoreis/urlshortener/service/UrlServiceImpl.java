package com.victoreis.urlshortener.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.victoreis.urlshortener.entity.Url;
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
        shortUrl.setAccessCount(0L);
        shortUrl.setCreatedAt(LocalDateTime.now());

        return repository.save(shortUrl);
    }

    @Override
    public Url findByShortCode(String shortCode) {
        return repository.findByShortCode(shortCode)
        .orElseThrow(()-> new RuntimeException("URL não encontrada"));
    }

}
