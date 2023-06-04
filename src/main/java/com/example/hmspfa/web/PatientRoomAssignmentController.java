package com.example.hmspfa.web;

import com.example.hmspfa.entities.PatientRoomAssignment;
import com.example.hmspfa.services.PatientRoomAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("api/v1/assignments")
@AllArgsConstructor
public class PatientRoomAssignmentController {

    private final PatientRoomAssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<PatientRoomAssignment>> getAllAssignments() {
        List<PatientRoomAssignment> assignments = assignmentService.getAllAssignments();
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<PatientRoomAssignment>> getAssignmentByRoomNumber(@PathVariable Long roomId) {
        List<PatientRoomAssignment> assignments = assignmentService.getAssignmentByRoomNumber(roomId);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping
    public ResponseEntity<PatientRoomAssignment> createAssignment(@RequestBody PatientRoomAssignment assignment) {
        PatientRoomAssignment savedAssignment = assignmentService.savePatientRoomAssignment(assignment);
        return ResponseEntity.ok(savedAssignment);
    }

    @PostMapping("patient/{patientId}/room/{roomId}")
    public ResponseEntity<PatientRoomAssignment> addAssignment(@RequestBody PatientRoomAssignment assignment
    , @PathVariable Long patientId,@PathVariable Long roomId) {
        PatientRoomAssignment savedAssignment = assignmentService.addPatientRoomAssignment(assignment,patientId,roomId);
        return ResponseEntity.ok(savedAssignment);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PatientRoomAssignment> getAssignmentById(@PathVariable("id") Long id) {
        PatientRoomAssignment assignment = assignmentService.getPatientRoomAssignmentById(id);
        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assignment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable("id") Long id) {
        assignmentService.deletePatientRoomAssignment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientRoomAssignment> updateAssignment(@PathVariable("id") Long id, @RequestBody PatientRoomAssignment assignment) {
        PatientRoomAssignment existingAssignment = assignmentService.getPatientRoomAssignmentById(id);
        if (existingAssignment == null) {
            return ResponseEntity.notFound().build();
        }
       // assignment.setId(id);
        PatientRoomAssignment updatedAssignment = assignmentService.updatePatientRoomAssignment(assignment);
        return ResponseEntity.ok(updatedAssignment);
    }


}
