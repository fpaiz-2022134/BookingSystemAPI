package com.francopaiz.bookingSystemAPI.service.usuario;

import com.francopaiz.bookingSystemAPI.model.user.User;
import com.francopaiz.bookingSystemAPI.repository.usuario.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(String id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }


        return userRepository.save(existingUser);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}