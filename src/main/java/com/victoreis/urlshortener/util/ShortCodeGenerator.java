package com.victoreis.urlshortener.util;

import java.security.SecureRandom;

public final class ShortCodeGenerator {

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    private ShortCodeGenerator() {
        
    }

    public static String generate(int length){
        StringBuilder shortCode = new StringBuilder(length);

        for(int i = 0; i < length; i++){
            int index = RANDOM.nextInt(BASE62.length());
            shortCode.append(BASE62.charAt(index));
        }
        return shortCode.toString();
    }   
}

