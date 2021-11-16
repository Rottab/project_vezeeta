package com.sumerge.vezeeta.services;

import com.sumerge.vezeeta.models.Role;
import com.sumerge.vezeeta.models.RoleEnum;
import com.sumerge.vezeeta.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    final private RoleEnum DEFAULT_ROLE = RoleEnum.ROLE_USER;

    public Role getDefault() {
        Role defaultRole = roleRepository.findByName(DEFAULT_ROLE);
        if (defaultRole == null) {
            defaultRole = new Role(DEFAULT_ROLE);
            roleRepository.save(defaultRole);
        }
        return defaultRole;
    }

    public boolean validateRoles(Set<Role> roles) {
        return false;
    }
}
