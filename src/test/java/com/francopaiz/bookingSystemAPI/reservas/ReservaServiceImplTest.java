package com.francopaiz.bookingSystemAPI.reservas;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import com.francopaiz.bookingSystemAPI.repository.reserva.ReservaRepository;
import com.francopaiz.bookingSystemAPI.service.reserva.ReservaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaServiceImpl reservaService;

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        reserva = new Reserva("user1", "Sala de conferencias", 150.0);
    }

    @Test
    void findAll_ShouldReturnListOfReservas() {
        // Arrange
        List<Reserva> expectedReservas = Arrays.asList(reserva);
        when(reservaRepository.findAll()).thenReturn(expectedReservas);

        // Act
        List<Reserva> result = reservaService.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(reserva, result.get(0));
        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnReserva_WhenReservaExists() {
        // Arrange
        when(reservaRepository.findById(anyString())).thenReturn(Optional.of(reserva));

        // Act
        Reserva result = reservaService.findById("1");

        // Assert
        assertNotNull(result);
        assertEquals("Sala de conferencias", result.getUbicacion());
        verify(reservaRepository, times(1)).findById("1");
    }

    @Test
    void findById_ShouldReturnNull_WhenReservaDoesNotExist() {
        // Arrange
        when(reservaRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act
        Reserva result = reservaService.findById("1");

        // Assert
        assertNull(result);
        verify(reservaRepository, times(1)).findById("1");
    }

    @Test
    void save_ShouldReturnSavedReserva() {
        // Arrange
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        // Act
        Reserva result = reservaService.save(reserva);

        // Assert
        assertNotNull(result);
        assertEquals("Sala de conferencias", result.getUbicacion());
        assertNotNull(result.getFechaReserva()); // Verificar que se haya asignado la fecha correctamente
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void update_ShouldReturnUpdatedReserva() {
        // Arrange
        Reserva updatedReserva = new Reserva("user2", "Oficina", 200.0);
        updatedReserva.setFechaReserva(LocalDateTime.now()); // Ajuste necesario para fecha

        when(reservaRepository.findById(anyString())).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(updatedReserva);

        // Act
        Reserva result = reservaService.update("1", updatedReserva);

        // Assert
        assertNotNull(result);
        assertEquals("Oficina", result.getUbicacion());
        assertEquals("user2", result.getUsuarioId());
        assertEquals(200.0, result.getCosto());
        verify(reservaRepository, times(1)).findById("1");
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    void update_ShouldThrowException_WhenReservaDoesNotExist() {
        // Arrange
        when(reservaRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.update("1", reserva));
        verify(reservaRepository, times(1)).findById("1");
        verify(reservaRepository, times(0)).save(any(Reserva.class));
    }

    @Test
    void deleteById_ShouldCallRepositoryDeleteById() {
        // Act
        reservaService.deleteById("1");

        // Assert
        verify(reservaRepository, times(1)).deleteById("1");
    }
}