package com.francopaiz.bookingSystemAPI.security.encrypt;

public interface PasswordEncryptionService {

    String encrypt(String password);
    boolean isPasswordMatch(String password, String encryptedPassword);

}
