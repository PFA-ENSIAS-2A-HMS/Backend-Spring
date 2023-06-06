package com.example.hmspfa.web;

import com.example.hmspfa.entities.SuperAdmin;
import com.example.hmspfa.entities.User;
import com.example.hmspfa.exceptions.UserNotFoundException;
import com.example.hmspfa.services.SuperAdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/super/admin")
@AllArgsConstructor
public class SuperAdminController {
    private SuperAdminService superAdminService;
    @PostMapping
    public SuperAdmin saveSuperAdmin(@RequestBody SuperAdmin user) {
        return superAdminService.saveSuperAdmin(user);
    }

    @PostMapping("/{id}")
    public SuperAdmin getSuperAdminById(@PathVariable Long id) throws UserNotFoundException {
        return superAdminService.getSuperAdminById(id);
    }


    @PostMapping("/update/{sId}")
    public User updateSuperAdmin(@RequestBody SuperAdmin user, @PathVariable Long sId) throws UserNotFoundException {
        return superAdminService.updateSuperAdmin(user,sId);
    }
}
