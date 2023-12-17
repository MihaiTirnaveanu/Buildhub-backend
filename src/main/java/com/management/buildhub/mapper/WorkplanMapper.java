package com.management.buildhub.mapper;

import com.management.buildhub.dtos.WorkplanDto;
import com.management.buildhub.models.Workplan;

public class WorkplanMapper {

    public static WorkplanDto convertToDto(Workplan workplan) {
        WorkplanDto workplanDto = new WorkplanDto();
        workplanDto.setId(workplan.getId());
        workplanDto.setName(workplan.getName());
        workplanDto.setStartDate(workplan.getStartDate());
        workplanDto.setEndDate(workplan.getEndDate());
        workplanDto.setDescription(workplan.getDescription());
        workplanDto.setWorksite(workplan.getWorksite());
        workplanDto.setStatus(workplan.getStatus());

        return workplanDto;
    }

    public static Workplan convertToEntity(WorkplanDto workplanDto) {
        Workplan workplan = new Workplan();
        workplan.setName(workplanDto.getName());
        workplan.setStartDate(workplanDto.getStartDate());
        workplan.setEndDate(workplanDto.getEndDate());
        workplan.setDescription(workplanDto.getDescription());
        workplan.setWorksite(workplanDto.getWorksite());
        workplan.setStatus(workplanDto.getStatus());

        return workplan;
    }
}
