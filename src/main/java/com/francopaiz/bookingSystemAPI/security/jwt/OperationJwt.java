package com.francopaiz.bookingSystemAPI.security.jwt;

import com.francopaiz.bookingSystemAPI.dto.UserRepositoryDto;

import java.util.Calendar;

public interface OperationJwt {

    String generateToken(UserRepositoryDto user, Calendar calendar);
}