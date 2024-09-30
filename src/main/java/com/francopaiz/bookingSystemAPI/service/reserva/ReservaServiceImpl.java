package com.francopaiz.bookingSystemAPI.service.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import com.francopaiz.bookingSystemAPI.repository.reserva.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva findById(String id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    public Reserva save(Reserva reserva) {
        reserva.setFechaReserva(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva update(String id, Reserva reserva) {
        Reserva existingReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        if (reserva.getUsuarioId() != null) {
            existingReserva.setUsuarioId(reserva.getUsuarioId());
        }
        if (reserva.getFechaReserva() != null) {
            existingReserva.setFechaReserva(reserva.getFechaReserva());
        }
        if (reserva.getUbicacion() != null) {
            existingReserva.setUbicacion(reserva.getUbicacion());
        }
        if (reserva.getCosto() != 0) {
            existingReserva.setCosto(reserva.getCosto());
        }


        return reservaRepository.save(existingReserva);
    }

    @Override
    public void deleteById(String id) {
        reservaRepository.deleteById(id);
    }
}