package com.francopaiz.bookingSystemAPI.repository.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository {
    Reserva guardar(Reserva reserva);
    Optional<Reserva> obtenerPorId(String id);
    List<Reserva> obtenerTodas();
    void eliminar(String id);
}