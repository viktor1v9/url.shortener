package com.victoreis.urlshortener.service;

import com.victoreis.urlshortener.entity.Url;

public interface UrlService {

    Url createShortUrl(String originalUrl);
    Url findByShortCode(String shortCode);
}
