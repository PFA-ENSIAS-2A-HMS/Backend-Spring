package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.SuperAdmin;
import com.example.hmspfa.entities.User;
import com.example.hmspfa.exceptions.UserNotFoundException;
import com.example.hmspfa.repositories.SuperAdminRepository;
import com.example.hmspfa.services.SuperAdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SuperAdminServiceImpl implements SuperAdminService {
    private final SuperAdminRepository superAdminRepository;
    @Override
    public SuperAdmin saveSuperAdmin(SuperAdmin user) {
        return superAdminRepository.save(user);
    }

    @Override
    public SuperAdmin getSuperAdminById(Long id) throws UserNotFoundException {
        Optional<SuperAdmin> superAdmin  = superAdminRepository.findById(id);
        if(!superAdmin.isPresent()){
            throw new UserNotFoundException("Super Admin Not Found !");
        }
        SuperAdmin superAdmin1  = superAdmin.get();
        return superAdmin1;
    }

    @Override
    public User updateSuperAdmin(SuperAdmin user,Long sId) throws UserNotFoundException {
        user.setId(sId);
        return superAdminRepository.save(user);
    }
}
