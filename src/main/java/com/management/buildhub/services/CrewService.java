package com.management.buildhub.services;

import com.management.buildhub.dtos.CrewDto;
import com.management.buildhub.mapper.CrewMapper;
import com.management.buildhub.models.Crew;
import com.management.buildhub.repos.CrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    @Autowired
    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<CrewDto> getAllCrews() {
        List<Crew> crews = crewRepository.findAll();
        return crews.stream()
                .map(CrewMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<CrewDto> getCrewById(Long id) {
        Optional<Crew> crewOptional = crewRepository.findById(id);
        return crewOptional.map(CrewMapper::convertToDto);
    }

    public CrewDto createCrew(CrewDto crewDto) {
        Crew crew = CrewMapper.convertToEntity(crewDto);
        Crew createdCrew = crewRepository.save(crew);
        return CrewMapper.convertToDto(createdCrew);
    }

    public CrewDto updateCrew(CrewDto updatedCrewDto) {
        Long id = updatedCrewDto.getId();
        Optional<Crew> existingCrewOptional = crewRepository.findById(id);

        if (existingCrewOptional.isPresent()) {
            Crew existingCrew = existingCrewOptional.get();
            Crew updatedCrew = CrewMapper.convertToEntity(updatedCrewDto);
            existingCrew.setName(updatedCrew.getName());
            existingCrew.setDetails(updatedCrew.getDetails());
            existingCrew.setTasks(updatedCrew.getTasks());
            Crew savedCrew = crewRepository.save(existingCrew);
            return CrewMapper.convertToDto(savedCrew);
        }

        return null;
    }


    public void deleteCrewById(Long id) {
        crewRepository.deleteById(id);
    }
}
