package com.management.buildhub.services;

import com.management.buildhub.dtos.ObjectiveDto;
import com.management.buildhub.mapper.ObjectiveMapper;
import com.management.buildhub.models.Objective;
import com.management.buildhub.repos.ObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObjectiveService {

    private final ObjectiveRepository objectiveRepository;

    @Autowired
    public ObjectiveService(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    public List<ObjectiveDto> getAllObjectives() {
        List<Objective> objectives = objectiveRepository.findAll();
        return objectives.stream()
                .map(ObjectiveMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ObjectiveDto> getObjectiveById(Long id) {
        Optional<Objective> objectiveOptional = objectiveRepository.findById(id);
        return objectiveOptional.map(ObjectiveMapper::convertToDto);
    }

    public ObjectiveDto createObjective(ObjectiveDto objectiveDto) {
        Objective objective = ObjectiveMapper.convertToEntity(objectiveDto);
        Objective createdObjective = objectiveRepository.save(objective);
        return ObjectiveMapper.convertToDto(createdObjective);
    }

    public ObjectiveDto updateObjective(ObjectiveDto updatedObjectiveDto) {
        Long id = updatedObjectiveDto.getId();
        Optional<Objective> existingObjectiveOptional = objectiveRepository.findById(id);

        if (existingObjectiveOptional.isPresent()) {
            Objective existingObjective = existingObjectiveOptional.get();
            Objective updatedObjective = ObjectiveMapper.convertToEntity(updatedObjectiveDto);
            // Update fields as needed
            existingObjective.setName(updatedObjective.getName());
            existingObjective.setStartDate(updatedObjective.getStartDate());
            existingObjective.setEndDate(updatedObjective.getEndDate());
            // Update other fields accordingly
            Objective savedObjective = objectiveRepository.save(existingObjective);
            return ObjectiveMapper.convertToDto(savedObjective);
        }

        return null;
    }

    public void deleteObjectiveById(Long id) {
        objectiveRepository.deleteById(id);
    }
}
