package com.francopaiz.bookingSystemAPI.service.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> findAll();
    Reserva findById(String id);
    Reserva save(Reserva reserva);
    Reserva update(String id, Reserva reserva);
    void deleteById(String id);
}