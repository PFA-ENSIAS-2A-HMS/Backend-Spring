package com.example.hmspfa.web;

import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.Room;
import com.example.hmspfa.services.HospitalService;
import com.example.hmspfa.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final HospitalService hospitalService;
    @PostMapping("/{hospitalId}")
    public Room saveRoom(@RequestBody Room room,@PathVariable Long hospitalId) {
        return roomService.saveRoom(room,hospitalId);
    }

    @GetMapping("{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/hospital/{hospitalId}")
    public List<Room> getRoomByHospitalId(@PathVariable Long hospitalId) {
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        return roomService.getRoomByHospital(hospital);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PutMapping
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
