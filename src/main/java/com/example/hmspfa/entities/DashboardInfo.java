package com.example.hmspfa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DashboardInfo {
    private int totalDoctors;
    private int totalPatients;
    private int totalAppointments;
    private int totalRooms;
    
}