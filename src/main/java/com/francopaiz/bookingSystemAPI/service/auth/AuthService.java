package com.francopaiz.bookingSystemAPI.service.auth;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import com.francopaiz.bookingSystemAPI.repository.usuario.UsuarioRepository;
import com.francopaiz.bookingSystemAPI.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        System.out.println(password + "  /   "+ usuario.getPassword());
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            System.out.println(usuario);
            return jwtTokenUtil.generateToken(usuario.getId());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
