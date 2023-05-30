package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.services.AdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Override
    public Admin saveAdmin(Admin admin) {
        return null;
    }

    @Override
    public Admin getAdminById(Long id) {
        return null;
    }

    @Override
    public void deleteAdmin(Long id) {

    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return null;
    }
}
