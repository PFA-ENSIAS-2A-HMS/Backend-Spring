package com.example.hmspfa.services;

import com.example.hmspfa.entities.SuperAdmin;
import com.example.hmspfa.entities.User;
import com.example.hmspfa.exceptions.UserNotFoundException;

import java.util.List;


public interface SuperAdminService {
    SuperAdmin saveSuperAdmin(SuperAdmin user);
    SuperAdmin getSuperAdminById(Long id) throws UserNotFoundException;

    User updateSuperAdmin(SuperAdmin user, Long sId) throws UserNotFoundException;
}
