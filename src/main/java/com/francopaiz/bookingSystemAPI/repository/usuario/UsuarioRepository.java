package com.francopaiz.bookingSystemAPI.repository.usuario;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {  // Cambia a JpaRepository si usas JPA
    Usuario findByEmail(String email);
}