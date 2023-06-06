package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByHospital(Hospital hospital);
}
