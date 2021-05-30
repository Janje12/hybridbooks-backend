package com.example.hybridbooksbackend.exception;

import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException implements Serializable {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
