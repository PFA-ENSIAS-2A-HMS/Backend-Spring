package com.example.hmspfa.services;


import com.example.hmspfa.entities.Admin;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    Admin getAdminById(Long id);
    void deleteAdmin(Long id);
    Admin updateAdmin(Admin admin);
}
