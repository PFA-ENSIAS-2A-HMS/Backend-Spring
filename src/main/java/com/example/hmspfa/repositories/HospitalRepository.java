package com.example.hmspfa.repositories;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Long> {


}
