package com.ca.fire.cache.support.command;

/**
 * @author
 */
public class CacheCommandException extends RuntimeException {
    public CacheCommandException(String message) {
        super(message);
    }

    public CacheCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
