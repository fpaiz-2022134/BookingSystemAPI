package com.francopaiz.bookingSystemAPI.service.usuario;

import com.francopaiz.bookingSystemAPI.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario crearUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorId(String id);
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario actualizarUsuario(String id, Usuario usuario);
    void eliminarUsuario(String id);
}
