package com.victoreis.urlshortener.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name="urls",
    indexes = {
        @Index(name = "idx_short_code", columnList = "short_code", unique = true),
    }
)
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Url {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;

    @Column(name = "short_code", nullable = false, unique = true, length = 10)
    private String shortCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "access_count", nullable = false)
    private Long accessCount;
}
