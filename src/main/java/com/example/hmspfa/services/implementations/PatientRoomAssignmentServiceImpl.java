package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Patient;
import com.example.hmspfa.entities.PatientRoomAssignment;
import com.example.hmspfa.entities.Room;
import com.example.hmspfa.exceptions.PatientNotFoundException;
import com.example.hmspfa.exceptions.PatientRoomAssignmentNotFoundException;
import com.example.hmspfa.exceptions.RoomNotFoundException;
import com.example.hmspfa.exceptions.RoomOccupiedException;
import com.example.hmspfa.repositories.PatientRepository;
import com.example.hmspfa.repositories.PatientRoomAssignmentRepository;
import com.example.hmspfa.repositories.RoomRepository;
import com.example.hmspfa.services.PatientRoomAssignmentService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PatientRoomAssignmentServiceImpl implements PatientRoomAssignmentService {

    private final PatientRoomAssignmentRepository assignmentRepository;
    private final PatientRepository patientRepository;

    private final RoomRepository roomRepository;

    @Override
    public PatientRoomAssignment savePatientRoomAssignment(PatientRoomAssignment assignment) {
        Room room = assignment.getRoom();
        if (assignment.getAdmissionDate().isBefore(LocalDate.now())) {
            throw new PatientRoomAssignmentNotFoundException("Error: Admission date has already passed.");
        }
        if (assignment.getDischargeDate().isBefore(assignment.getAdmissionDate())) {
            String errorMessage = "Error: Discharge date is before the admission date.";
            throw new PatientRoomAssignmentNotFoundException(errorMessage);
        }
        if(room.getOccupiedBeds()<room.getRoomCapacity()){
            room.setOccupiedBeds(room.getOccupiedBeds()+1);
            roomRepository.save(room);
            return assignmentRepository.save(assignment);
        }else{
            throw new RoomOccupiedException("The room is already occupied.");
        }
    }

    @Override
    public PatientRoomAssignment addPatientRoomAssignment(PatientRoomAssignment assignment,
                                                          Long patientId, Long roomId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent()){
            throw new PatientNotFoundException("Patient Not Found");
        }
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if(!roomOptional.isPresent()){
            throw new RoomNotFoundException("Room Not found");
        }
        Room room = roomOptional.get();
        room.setOccupiedBeds(room.getOccupiedBeds()+1);
        roomRepository.save(room);
        Patient patient = patientOptional.get();
        assignment.setRoom(room);
        assignment.setPatient(patient);
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<PatientRoomAssignment> getAssignmentByRoomNumber(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if(!roomOptional.isPresent()){
            throw new RoomNotFoundException("Room Not Found");
        }
        Room room = roomOptional.get();
        return assignmentRepository.findByRoom(room);
    }

    @Override
    public PatientRoomAssignment getPatientRoomAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePatientRoomAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public PatientRoomAssignment updatePatientRoomAssignment(PatientRoomAssignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<PatientRoomAssignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }


}
