package com.francopaiz.bookingSystemAPI.controller.auth;


import com.francopaiz.bookingSystemAPI.model.user.RoleEnum;
import com.francopaiz.bookingSystemAPI.model.user.User;
import com.francopaiz.bookingSystemAPI.security.jwt.JwtUtil;
import com.francopaiz.bookingSystemAPI.service.usuario.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final User user = new User();

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {

        TokenDto tokenDto = jwtUtil.generateToken(loginDto.getUsername(), user.getRoles());
        System.out.println(tokenDto);
        return ResponseEntity.ok(tokenDto);
    }


}