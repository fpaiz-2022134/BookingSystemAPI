package com.francopaiz.bookingSystemAPI.auth;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import com.francopaiz.bookingSystemAPI.repository.usuario.UsuarioRepository;
import com.francopaiz.bookingSystemAPI.security.JwtTokenUtil;
import com.francopaiz.bookingSystemAPI.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private AuthService authService;

    private BCryptPasswordEncoder passwordEncoder;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Inicializamos los mocks
        MockitoAnnotations.openMocks(this);

        // Inicializamos el encriptador de contraseñas
        passwordEncoder = new BCryptPasswordEncoder();

        usuario = new Usuario("user1", "test@example.com", "password123", "12345678");
    }


    @Test
    void login_ShouldThrowException_WhenPasswordIsIncorrect() {
        // Arrange
        when(usuarioRepository.findByEmail(anyString())).thenReturn(usuario);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authService.login("test@example.com", "wrongPassword"));
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
        verify(jwtTokenUtil, times(0)).generateToken(anyString());
    }

    @Test
    void login_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        when(usuarioRepository.findByEmail(anyString())).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authService.login("nonexistent@example.com", "password123"));
        verify(usuarioRepository, times(1)).findByEmail("nonexistent@example.com");
        verify(jwtTokenUtil, times(0)).generateToken(anyString());
    }
}