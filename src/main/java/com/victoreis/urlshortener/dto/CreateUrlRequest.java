package com.victoreis.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
public class CreateUrlRequest {

    @NotBlank
    @Pattern(
        regexp = "^(http|https)://.*$",
        message = "URL deve começar com http:// ou https://"
    )
    private String originalUrl;
}
