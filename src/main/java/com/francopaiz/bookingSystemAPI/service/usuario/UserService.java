package com.francopaiz.bookingSystemAPI.service.usuario;

import com.francopaiz.bookingSystemAPI.model.user.RoleEnum;
import com.francopaiz.bookingSystemAPI.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(String id);
    User save(User user);
    User update(String id, User usuario);
    void deleteById(String id);


}
