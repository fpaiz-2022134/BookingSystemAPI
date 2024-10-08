package com.francopaiz.bookingSystemAPI.service.usuario;

import com.francopaiz.bookingSystemAPI.model.Reserva;
import com.francopaiz.bookingSystemAPI.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();
    Usuario findById(String id);
    Usuario save(Usuario usuario);
    Usuario update(String id, Usuario usuario);
    void deleteById(String id);

    UserDetails loadUserByUsername(String username);
}
