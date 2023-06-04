package com.example.hmspfa.services;

import com.example.hmspfa.entities.Room;
import com.example.hmspfa.exceptions.RoomNotFoundException;

import java.util.List;

public interface RoomService {
    Room saveRoom(Room room, Long hospitalId);
    Room getRoomById(Long id) throws RoomNotFoundException;
    void deleteRoom(Long id) throws RoomNotFoundException;
    Room updateRoom(Room room) throws RoomNotFoundException;

    List<Room> getAllRooms();
}
