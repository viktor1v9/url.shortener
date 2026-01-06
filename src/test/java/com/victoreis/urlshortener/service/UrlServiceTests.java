package com.victoreis.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.victoreis.urlshortener.entity.Url;
import com.victoreis.urlshortener.exception.ResourceNotFoundException;
import com.victoreis.urlshortener.repository.UrlRepository;

@ExtendWith(MockitoExtension.class)
class UrlServiceTests {

    @Mock
    private UrlRepository repository;

    @InjectMocks
    private UrlServiceImpl service;

    @Test
    void shouldCreateShortUrl() {
        Url saved = new Url();
        saved.setShortCode("ABC123");   

        when(repository.save(any())).thenReturn(saved);

        Url result = service.createShortUrl("http://github.com");

        assertNotNull(result);
        assertEquals("ABC123", result.getShortCode());
    }

    @Test
    void shouldIncrementAccessCount() {
        Url shortUrl = new Url();
        shortUrl.setOriginalUrl("http://github.com");
        shortUrl.setShortCode("ABC123");
        shortUrl.setAccessCount(0);

        when(repository.findByShortCode("ABC123")).thenReturn(Optional.of(shortUrl));

        service.findByShortCode("ABC123");
        
        assertEquals(1, shortUrl.getAccessCount());
        verify(repository).save(shortUrl);
    }

    @Test
    void shouldThrowIfNotFound() {
        when(repository.findByShortCode("XXX")).thenReturn(Optional.empty());

        assertThrows(
            ResourceNotFoundException.class,
            () -> service.findByShortCode("XXX")
        );
    }

    @Test
    void shouldThrowIfExpired() {
        Url shortUrl = new Url();
        shortUrl.setShortCode("ABC123");
        shortUrl.setExpiresAt(Instant.now().minusSeconds(3600)); 

        when(repository.findByShortCode("ABC123")).thenReturn(Optional.of(shortUrl));

        assertThrows(
            ResourceNotFoundException.class,
            () -> service.findByShortCode("ABC123")
        );

    }
}
