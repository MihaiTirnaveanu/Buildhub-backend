package com.management.buildhub.services;

import com.management.buildhub.dtos.WorkplanDto;
import com.management.buildhub.mapper.WorkplanMapper;
import com.management.buildhub.models.Workplan;
import com.management.buildhub.repos.WorkplanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkplanService {

    private final WorkplanRepository workplanRepository;

    @Autowired
    public WorkplanService(WorkplanRepository workplanRepository) {
        this.workplanRepository = workplanRepository;
    }

    public List<WorkplanDto> getAllWorkplans() {
        List<Workplan> workplans = workplanRepository.findAll();
        return workplans.stream()
                .map(WorkplanMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<WorkplanDto> getWorkplanById(Long id) {
        Optional<Workplan> workplanOptional = workplanRepository.findById(id);
        return workplanOptional.map(WorkplanMapper::convertToDto);
    }

    public WorkplanDto createWorkplan(WorkplanDto workplanDto) {
        Workplan workplan = WorkplanMapper.convertToEntity(workplanDto);
        Workplan createdWorkplan = workplanRepository.save(workplan);
        return WorkplanMapper.convertToDto(createdWorkplan);
    }

    public WorkplanDto updateWorkplan(WorkplanDto updatedWorkplanDto) {
        Long id = updatedWorkplanDto.getId();
        Optional<Workplan> existingWorkplanOptional = workplanRepository.findById(id);

        if (existingWorkplanOptional.isPresent()) {
            Workplan existingWorkplan = existingWorkplanOptional.get();
            Workplan updatedWorkplan = WorkplanMapper.convertToEntity(updatedWorkplanDto);
            existingWorkplan.setName(updatedWorkplan.getName());
            existingWorkplan.setStartDate(updatedWorkplan.getStartDate());
            existingWorkplan.setEndDate(updatedWorkplan.getEndDate());
            existingWorkplan.setDescription(updatedWorkplan.getDescription());
            existingWorkplan.setWorksite(updatedWorkplan.getWorksite());
            existingWorkplan.setStatus(updatedWorkplan.getStatus());
            Workplan savedWorkplan = workplanRepository.save(existingWorkplan);
            return WorkplanMapper.convertToDto(savedWorkplan);
        }

        return null;
    }

    public void deleteWorkplanById(Long id) {
        workplanRepository.deleteById(id);
    }
}
