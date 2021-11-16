package com.sumerge.vezeeta.controllers;

import com.sumerge.vezeeta.controllers.models.requests.AppUserPatchRequest;
import com.sumerge.vezeeta.controllers.models.requests.AppUserPutRequest;
import com.sumerge.vezeeta.models.AppUser;
import com.sumerge.vezeeta.security.models.AppUserDetails;
import com.sumerge.vezeeta.security.services.AppUserDetailsService;
import com.sumerge.vezeeta.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/users")
public class AppUsersController {
    @Autowired
    private AppUserService userService;


    @GetMapping
    public ResponseEntity<AppUser> getCurrentUser(@AuthenticationPrincipal AppUserDetails userDetails) {
        AppUser appUser = userService.getUser(userDetails.getId());
        return ResponseEntity.ok(appUser);
    }

    @PutMapping
    public ResponseEntity<?> updateCurrentUser(@Valid @RequestBody AppUserPutRequest request, @AuthenticationPrincipal AppUserDetails userDetails) {
        AppUser appUser = userService.getUser(userDetails.getId());
        AppUser updatedUser = userService.updateUser(appUser, request.getFirstName(), request.getLastName(), request.getEmail(), request.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<?> updateCurrentUserPassword(@Valid @RequestBody AppUserPatchRequest request, @AuthenticationPrincipal AppUserDetails userDetails) {
        AppUser appUser = userService.getUser(userDetails.getId());
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
