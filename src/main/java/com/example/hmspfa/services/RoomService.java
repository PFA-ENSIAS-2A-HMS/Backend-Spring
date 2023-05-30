package com.example.hmspfa.services;

import com.example.hmspfa.entities.Room;

import java.util.List;

public interface RoomService {
    Room saveRoom(Room room);
    Room getRoomById(Long id);
    void deleteRoom(Long id);
    Room updateRoom(Room room);

    List<Room> getAllRooms();
}
