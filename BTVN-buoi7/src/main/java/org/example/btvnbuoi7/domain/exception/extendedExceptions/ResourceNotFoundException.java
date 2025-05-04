package org.example.btvnbuoi7.domain.exception.extendedExceptions;

public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
}
