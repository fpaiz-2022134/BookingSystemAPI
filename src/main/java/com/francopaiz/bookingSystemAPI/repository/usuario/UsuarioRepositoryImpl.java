package com.francopaiz.bookingSystemAPI.repository.usuario;

import com.francopaiz.bookingSystemAPI.model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Map;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private Map<String, Usuario> usuarios = new HashMap<>();

    @Override
    public Usuario guardar(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }


    @Override
    public Optional<Usuario> obtenerPorId(String id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(usuarios.values());
    }

    @Override
    public void eliminar(String id) {
        usuarios.remove(id);
    }
}