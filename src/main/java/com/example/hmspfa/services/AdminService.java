package com.example.hmspfa.services;


import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.exceptions.AdminNotFoundException;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);
    Admin getAdminById(Long id) throws AdminNotFoundException;
    void deleteAdmin(Long id) throws AdminNotFoundException;
    Admin updateAdmin(Admin admin) throws AdminNotFoundException;
    List<Admin> getAllAdmins();
}
