package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.exceptions.DataNotFoundException;
import com.sumerge.vezeeta.exceptions.DataConstrainViolationException;
import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.utils.ConstrainHandler;
import com.sumerge.vezeeta.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    public void addUser(AppUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            ConstrainHandler.handle(e.getLocalizedMessage());
        }
    }


    @Transactional
    public AppUser getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new DataNotFoundException(AppUser.class, id));
    }

    // I don't like this
    @Transactional
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public AppUser updateUser(AppUser user, String firstName, String lastName, String email, String username) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            ConstrainHandler.handle(e.getLocalizedMessage());
        }
        return user;
    }
}
