package com.francopaiz.bookingSystemAPI.service.usuario;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import com.francopaiz.bookingSystemAPI.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }



    @Override
    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        return usuarioRepository.obtenerPorId(id);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.obtenerTodos();
    }

    @Override
    public Usuario actualizarUsuario(String id, Usuario usuario) {
        return usuarioRepository.guardar(usuario);
    }

    @Override
    public void eliminarUsuario(String id) {
        usuarioRepository.eliminar(id);
    }
}
