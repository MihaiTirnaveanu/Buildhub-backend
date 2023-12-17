package com.management.buildhub.controllers;

import com.management.buildhub.dtos.WorkplanDto;
import com.management.buildhub.services.WorkplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/workplans")
@CrossOrigin(origins = "http://localhost:4200/")
public class WorkplanController {

    private final WorkplanService workplanService;

    @Autowired
    public WorkplanController(WorkplanService workplanService) {
        this.workplanService = workplanService;
    }

    @GetMapping
    public ResponseEntity<List<WorkplanDto>> getAllWorkplans() {
        List<WorkplanDto> workplanDtos = workplanService.getAllWorkplans();
        return new ResponseEntity<>(workplanDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkplanDto> getWorkplanById(@PathVariable Long id) {
        Optional<WorkplanDto> workplanOptional = workplanService.getWorkplanById(id);
        return workplanOptional.map(workplan -> new ResponseEntity<>(workplan, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<WorkplanDto> createWorkplan(@RequestBody WorkplanDto workplanDto) {
        WorkplanDto createdWorkplan = workplanService.createWorkplan(workplanDto);
        return new ResponseEntity<>(createdWorkplan, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<WorkplanDto> updateWorkplan(@RequestBody WorkplanDto updatedWorkplanDto) {
        WorkplanDto updatedWorkplan = workplanService.updateWorkplan(updatedWorkplanDto);
        if (updatedWorkplan != null) {
            return new ResponseEntity<>(updatedWorkplan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkplanById(@PathVariable Long id) {
        workplanService.deleteWorkplanById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
