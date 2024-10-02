package com.francopaiz.bookingSystemAPI.security.encrypt;

public interface PasswordEncryptionService {
    String encypt(String password);
    boolean isPasswordMatch(String password, String encryptedPassword);
}
