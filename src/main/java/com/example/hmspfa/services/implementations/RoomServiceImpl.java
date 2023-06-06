package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Room;
import com.example.hmspfa.exceptions.HospitalNotFoundException;
import com.example.hmspfa.exceptions.RoomNotFoundException;
import com.example.hmspfa.repositories.HospitalRepository;
import com.example.hmspfa.repositories.RoomRepository;
import com.example.hmspfa.services.RoomService;
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
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public Room saveRoom(Room room, Long hospitalId) {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalId);
        if(!hospitalOptional.isPresent()){
            throw new HospitalNotFoundException("Hospital Not Found");
        }
        Hospital hospital= hospitalOptional.get();
        room.setHospital(hospital);
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Long id) throws RoomNotFoundException {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Room Not Found"));
    }


    @Override
    public void deleteRoom(Long id) throws RoomNotFoundException {
        Optional<Room> roomOptional = roomRepository.findById(id);

        if (roomOptional.isEmpty()) {
            throw new RoomNotFoundException("Room Not Found");
        }

        roomRepository.deleteById(id);
    }

    @Override
    public Room updateRoom(Room room) {
        Optional<Room> roomOptional = roomRepository.findById(room.getId());
        if (roomOptional.isEmpty()) {
            throw new RoomNotFoundException("Room Not Found");
        }
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public  List<Room> getRoomByHospital(Hospital hospital) {
        return roomRepository.findByHospital(hospital);
    }
}
