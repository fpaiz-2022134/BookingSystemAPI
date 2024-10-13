package com.francopaiz.bookingSystemAPI.service.usuario;

import com.francopaiz.bookingSystemAPI.model.user.RoleEnum;
import com.francopaiz.bookingSystemAPI.model.user.User;
import com.francopaiz.bookingSystemAPI.repository.usuario.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        System.out.println(userRepository.findById(id));
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        // Encriptar la contraseña antes de guardar
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));

        // Asegurarte de que la lista de roles no esté vacía
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            // Establecer un rol predeterminado, por ejemplo, ROLE_USER
            user.setRoles(new ArrayList<>(Collections.singletonList(RoleEnum.USER)));
        }

        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        // Actualizar la contraseña si se proporciona
        if (user.getPasswordHash() != null) {
            existingUser.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        }

        // Aquí podrías agregar lógica para manejar roles si es necesario
         if (user.getRoles() != null) {
             existingUser.setRoles(user.getRoles());
         }

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
