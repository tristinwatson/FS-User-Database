package com.fullstackapp.fullstackbackend.exception;

// exception for searching users
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("No user found with id " + id);
    }
}
