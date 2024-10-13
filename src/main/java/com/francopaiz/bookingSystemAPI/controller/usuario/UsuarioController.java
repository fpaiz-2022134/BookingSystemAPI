package com.francopaiz.bookingSystemAPI.controller.usuario;

import com.francopaiz.bookingSystemAPI.model.user.User;

import com.francopaiz.bookingSystemAPI.service.usuario.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UserService usuarioService;

    @GetMapping()
    public List<User> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping("/{idUsuario}")
    public User findById(@PathVariable String idUsuario){
        return usuarioService.findById(idUsuario);
    }

    @PostMapping()
    public User save( @RequestBody User usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/{idUsuario}")
    public User update(@PathVariable String idUsuario, @RequestBody User usuario){
        return usuarioService.update(idUsuario, usuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void deleteById(@PathVariable String idUsuario){
        usuarioService.deleteById(idUsuario);
    }
}
