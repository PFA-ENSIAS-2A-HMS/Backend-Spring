package com.example.hmspfa.web;

import com.example.hmspfa.entities.Receptionist;
import com.example.hmspfa.services.ReceptionistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/receptionists")
@AllArgsConstructor
public class ReceptionistController {

    private final ReceptionistService receptionistService;

    @PostMapping
    public Receptionist saveReceptionist(@RequestBody Receptionist receptionist) {
        return receptionistService.saveReceptionist(receptionist);
    }

    @GetMapping("{id}")
    public Receptionist getReceptionistById(@PathVariable Long id) {
        return receptionistService.getReceptionistById(id);
    }

    @GetMapping
    public List<Receptionist> getAllReceptionists() {
        return receptionistService.getAllReceptionists();
    }

    @PutMapping
    public Receptionist updateReceptionist(@RequestBody Receptionist receptionist) {
        return receptionistService.updateReceptionist(receptionist);
    }

    @DeleteMapping("{id}")
    public void deleteReceptionist(@PathVariable Long id) {
        receptionistService.deleteReceptionist(id);
    }
}
