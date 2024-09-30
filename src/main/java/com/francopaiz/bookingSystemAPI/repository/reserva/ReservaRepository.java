package com.francopaiz.bookingSystemAPI.repository.reserva;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
}