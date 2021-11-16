package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.controllers.models.requests.LoginRequest;
import com.sumerge.vezeeta.controllers.models.responses.LoginResponse;
import com.sumerge.vezeeta.controllers.models.requests.SignupRequest;
import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.models.Role;
import com.sumerge.vezeeta.models.RoleEnum;
import com.sumerge.vezeeta.security.models.AppUserDetails;
import com.sumerge.vezeeta.security.services.AppUserDetailsService;
import com.sumerge.vezeeta.services.RoleService;
import com.sumerge.vezeeta.utils.JwtUtil;
import com.sumerge.vezeeta.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AppUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AppUserService userService;
    @Autowired
    private RoleService roleService;
    @Value("${vezeeta.app.jwtExpireIn}")
    Integer expiresIn;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        AppUserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new LoginResponse(token, userDetails.getUsername(), expiresIn, roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUser> signup(@Valid @RequestBody SignupRequest request) {
        AppUser user = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword());
        // If no roles were provided add to user role
        Set<Role> roles = new HashSet<>();
        if (request.getRoles() == null) {
            roles.add(roleService.getDefault());
        } else {
            // Need to validate the roles
        }
        user.setRoles(roles);
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
