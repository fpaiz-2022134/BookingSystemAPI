package com.francopaiz.bookingSystemAPI.controller.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import com.francopaiz.bookingSystemAPI.service.reserva.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.crearReserva(reserva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable String id) {
        Optional<Reserva> reserva = reservaService.obtenerReservaPorId(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Reserva> obtenerTodasLasReservas() {
        return reservaService.obtenerTodasLasReservas();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable String id, @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.actualizarReserva(id, reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable String id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
