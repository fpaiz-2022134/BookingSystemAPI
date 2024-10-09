package com.francopaiz.bookingSystemAPI.repository.usuario;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    User findByEmail(String email);
}