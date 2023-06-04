package com.example.hmspfa.services;

import com.example.hmspfa.entities.PatientRoomAssignment;

import java.util.List;

public interface PatientRoomAssignmentService {
    PatientRoomAssignment savePatientRoomAssignment(PatientRoomAssignment assignment);
    PatientRoomAssignment getPatientRoomAssignmentById(Long id);
    void deletePatientRoomAssignment(Long id);
    PatientRoomAssignment updatePatientRoomAssignment(PatientRoomAssignment assignment);

    List<PatientRoomAssignment> getAllAssignments();

    PatientRoomAssignment addPatientRoomAssignment(PatientRoomAssignment assignment, Long patientId, Long roomId);

    List<PatientRoomAssignment> getAssignmentByRoomNumber(Long roomId);
}
