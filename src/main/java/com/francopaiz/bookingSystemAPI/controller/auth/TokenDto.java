package com.francopaiz.bookingSystemAPI.controller.auth;

import java.util.Date;

public record  TokenDto( String token,
        Date expirationDate){
}