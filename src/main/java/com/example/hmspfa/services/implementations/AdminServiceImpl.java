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
    public Admin getAdminById(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        } else {
            log.warn("Admin not found with ID: {}", id);
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
    }

    @Override
    public void deleteAdmin(Long id) {
        try {

            adminRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error while deleting the admin with ID: {}", id);
            throw new RuntimeException("Error while deleting the admin", e);
        }
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
