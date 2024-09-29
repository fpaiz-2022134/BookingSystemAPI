package com.francopaiz.bookingSystemAPI.service.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import com.francopaiz.bookingSystemAPI.repository.reserva.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.guardar(reserva);
    }


    @Override
    public Optional<Reserva> obtenerReservaPorId(String id) {
        return reservaRepository.obtenerPorId(id);
    }

    @Override
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.obtenerTodas();
    }

    @Override
    public Reserva actualizarReserva(String id, Reserva reserva) {
        return reservaRepository.guardar(reserva);
    }

    @Override
    public void eliminarReserva(String id) {
        reservaRepository.eliminar(id);
    }
}