package com.francopaiz.bookingSystemAPI.usuario;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import com.francopaiz.bookingSystemAPI.repository.usuario.UsuarioRepository;
import com.francopaiz.bookingSystemAPI.service.usuario.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario();
        usuario.setId("1");
        usuario.setNombre("Franco Paiz");
        usuario.setEmail("franco@example.com");
        usuario.setTelefono("123456789");
    }

    @Test
    void findAll_ShouldReturnListOfUsuarios() {
        // Arrange
        List<Usuario> expectedUsuarios = Arrays.asList(usuario);
        when(usuarioRepository.findAll()).thenReturn(expectedUsuarios);

        // Act
        List<Usuario> result = usuarioService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(usuario, result.get(0));
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnUsuario_WhenUsuarioExists() {
        // Arrange
        when(usuarioRepository.findById(anyString())).thenReturn(Optional.of(usuario));

        // Act
        Usuario result = usuarioService.findById("1");

        // Assert
        assertNotNull(result);
        assertEquals("Franco Paiz", result.getNombre());
        verify(usuarioRepository, times(1)).findById("1");
    }

    @Test
    void findById_ShouldReturnNull_WhenUsuarioDoesNotExist() {
        // Arrange
        when(usuarioRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act
        Usuario result = usuarioService.findById("1");

        // Assert
        assertNull(result);
        verify(usuarioRepository, times(1)).findById("1");
    }

    @Test
    void save_ShouldReturnSavedUsuario() {
        // Arrange
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        Usuario result = usuarioService.save(usuario);

        // Assert
        assertNotNull(result);
        assertEquals("Franco Paiz", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void update_ShouldReturnUpdatedUsuario() {
        // Arrange
        Usuario updatedUsuario = new Usuario();
        updatedUsuario.setNombre("Alejandro Paiz");
        updatedUsuario.setEmail("alejandro@example.com");
        updatedUsuario.setTelefono("987654321");

        when(usuarioRepository.findById(anyString())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(updatedUsuario);

        // Act
        Usuario result = usuarioService.update("1", updatedUsuario);

        // Assert
        assertNotNull(result);
        assertEquals("Alejandro Paiz", result.getNombre());
        assertEquals("alejandro@example.com", result.getEmail());
        verify(usuarioRepository, times(1)).findById("1");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void update_ShouldThrowException_WhenUsuarioDoesNotExist() {
        // Arrange
        when(usuarioRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuarioService.update("1", usuario));
        verify(usuarioRepository, times(1)).findById("1");
        verify(usuarioRepository, times(0)).save(any(Usuario.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDeleteById() {
        // Act
        usuarioService.deleteById("1");

        // Assert
        verify(usuarioRepository, times(1)).deleteById("1");
    }
}