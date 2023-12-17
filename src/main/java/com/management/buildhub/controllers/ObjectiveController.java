package com.management.buildhub.controllers;

import com.management.buildhub.dtos.ObjectiveDto;
import com.management.buildhub.services.ObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/objectives")
@CrossOrigin(origins = "http://localhost:4200/")
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    @Autowired
    public ObjectiveController(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @GetMapping
    public ResponseEntity<List<ObjectiveDto>> getAllObjectives() {
        List<ObjectiveDto> objectiveDtos = objectiveService.getAllObjectives();
        return new ResponseEntity<>(objectiveDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveDto> getObjectiveById(@PathVariable Long id) {
        Optional<ObjectiveDto> objectiveOptional = objectiveService.getObjectiveById(id);
        return objectiveOptional.map(objective -> new ResponseEntity<>(objective, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ObjectiveDto> createObjective(@RequestBody ObjectiveDto objectiveDto) {
        ObjectiveDto createdObjective = objectiveService.createObjective(objectiveDto);
        return new ResponseEntity<>(createdObjective, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ObjectiveDto> updateObjective(@RequestBody ObjectiveDto updatedObjectiveDto) {
        ObjectiveDto updatedObjective = objectiveService.updateObjective(updatedObjectiveDto);
        if (updatedObjective != null) {
            return new ResponseEntity<>(updatedObjective, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjectiveById(@PathVariable Long id) {
        objectiveService.deleteObjectiveById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
