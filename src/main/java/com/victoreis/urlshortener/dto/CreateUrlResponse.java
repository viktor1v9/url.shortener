package com.victoreis.urlshortener.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class CreateUrlResponse {
    private String shortCode;
    private String originalUrl;

}
