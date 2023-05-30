package com.example.hmspfa.services.implementations;

import com.example.hmspfa.entities.Room;
import com.example.hmspfa.services.RoomService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Override
    public Room saveRoom(Room room) {
        return null;
    }

    @Override
    public Room getRoomById(Long id) {
        return null;
    }

    @Override
    public void deleteRoom(Long id) {

    }

    @Override
    public Room updateRoom(Room room) {
        return null;
    }
}
