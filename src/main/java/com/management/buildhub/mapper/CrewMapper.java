package com.management.buildhub.mapper;

import com.management.buildhub.dtos.CrewDto;
import com.management.buildhub.models.Crew;
import com.management.buildhub.models.Task;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CrewMapper {

    public static CrewDto convertToDto(Crew crew) {
        CrewDto crewDto = new CrewDto();
        crewDto.setId(crew.getId());
        crewDto.setName(crew.getName());
        crewDto.setDetails(crew.getDetails());
        crewDto.setTaskIds(crew.getTasks().stream().map(Task::getId).collect(Collectors.toSet()));
        return crewDto;
    }

    public static Crew convertToEntity(CrewDto crewDto) {
        Crew crew = new Crew();
        crew.setName(crewDto.getName());
        crew.setDetails(crewDto.getDetails());

        Set<Task> tasks = new HashSet<>();
        if (crewDto.getTaskIds() != null) {
            for (Long taskId : crewDto.getTaskIds()) {
                Task task = new Task();
                task.setId(taskId);
                tasks.add(task);
            }
        }
        crew.setTasks(tasks);

        return crew;
    }
}
