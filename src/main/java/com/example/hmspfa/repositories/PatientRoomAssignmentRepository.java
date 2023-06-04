package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.PatientRoomAssignment;
import com.example.hmspfa.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRoomAssignmentRepository extends JpaRepository<PatientRoomAssignment,Long> {
    List<PatientRoomAssignment> findByRoom(Room room);
}
