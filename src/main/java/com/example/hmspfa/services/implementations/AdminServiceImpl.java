package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.exceptions.AdminNotFoundException;
import com.example.hmspfa.repositories.AdminRepository;
import com.example.hmspfa.services.AdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin saveAdmin(Admin admin) {
        try {
            return adminRepository.save(admin);
        } catch (Exception e) {
            log.error("Error while saving the admin: {}", e.getMessage());
            throw new RuntimeException("Error while saving the admin", e);
        }
    }

    @Override
    public Admin getAdminById(Long id) throws AdminNotFoundException {
        Admin admin = adminRepository.findById(id).orElseThrow(
                ()-> new AdminNotFoundException("Admin Nor Found")
        );
        return admin;
    }

    @Override
    public void deleteAdmin(Long id) throws AdminNotFoundException {
        Admin admin = adminRepository.findById(id).orElseThrow(
                ()-> new AdminNotFoundException("Admin Nor Found")
        );
            adminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        try {
            return adminRepository.save(admin);
        } catch (Exception e) {
            log.error("Error while updating the admin: {}", e.getMessage());
            throw new RuntimeException("Error while updating the admin", e);
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
