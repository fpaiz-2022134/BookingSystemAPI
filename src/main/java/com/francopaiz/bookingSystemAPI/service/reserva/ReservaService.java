package com.francopaiz.bookingSystemAPI.service.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    Reserva crearReserva(Reserva reserva);
    Optional<Reserva> obtenerReservaPorId(String id);
    List<Reserva> obtenerTodasLasReservas();
    Reserva actualizarReserva(String id, Reserva reserva);
    void eliminarReserva(String id);
}