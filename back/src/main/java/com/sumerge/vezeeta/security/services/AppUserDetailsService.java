package com.sumerge.vezeeta.security.services;

import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.repository.AppUserRepository;
import com.sumerge.vezeeta.security.exceptions.UserNotFoundException;
import com.sumerge.vezeeta.security.models.AppUserDetails;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    AppUserRepository userRepository;


    @Override
    @Transactional
    public AppUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + s + " not found"));
        return AppUserDetails.build(user);
    }

    @Transactional
    public AppUserDetails loadUserById(Integer id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return AppUserDetails.build(user);
    }
}
