package com.francopaiz.bookingSystemAPI.repository.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class ReservaRepositoryImpl implements ReservaRepository {
    private Map<String, Reserva> reservas = new HashMap<>();

    @Override
    public Reserva guardar(Reserva reserva) {
        reservas.put(reserva.getId(), reserva);
        return reserva;
    }

    @Override
    public Optional<Reserva> obtenerPorId(String id) {
        return Optional.ofNullable(reservas.get(id));
    }

    @Override
    public List<Reserva> obtenerTodas() {
        return new ArrayList<>(reservas.values());
    }

    @Override
    public void eliminar(String id) {
        reservas.remove(id);
    }
}