package com.management.buildhub.controllers;

import com.management.buildhub.dtos.CrewDto;
import com.management.buildhub.services.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/crews")
@CrossOrigin(origins = "http://localhost:4200/")
public class CrewController {

    private final CrewService crewService;

    @Autowired
    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping
    public ResponseEntity<List<CrewDto>> getAllCrews() {
        List<CrewDto> crewDtos = crewService.getAllCrews();
        return new ResponseEntity<>(crewDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewDto> getCrewById(@PathVariable Long id) {
        Optional<CrewDto> crewOptional = crewService.getCrewById(id);
        return crewOptional.map(crew -> new ResponseEntity<>(crew, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CrewDto> createCrew(@RequestBody CrewDto crewDto) {
        CrewDto createdCrew = crewService.createCrew(crewDto);
        return new ResponseEntity<>(createdCrew, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CrewDto> updateCrew(@RequestBody CrewDto updatedCrewDto) {
        CrewDto updatedCrew = crewService.updateCrew(updatedCrewDto);
        if (updatedCrew != null) {
            return new ResponseEntity<>(updatedCrew, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrewById(@PathVariable Long id) {
        crewService.deleteCrewById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
