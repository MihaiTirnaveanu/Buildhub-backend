package com.management.buildhub.mapper;

import com.management.buildhub.dtos.ObjectiveDto;
import com.management.buildhub.models.Objective;
import com.management.buildhub.models.Task;
import com.management.buildhub.models.Workplan;

import java.util.Set;
import java.util.stream.Collectors;

public class ObjectiveMapper {

    public static ObjectiveDto convertToDto(Objective objective) {
        ObjectiveDto objectiveDto = new ObjectiveDto();
        objectiveDto.setId(objective.getId());
        objectiveDto.setName(objective.getName());
        objectiveDto.setStartDate(objective.getStartDate());
        objectiveDto.setEndDate(objective.getEndDate());
        objectiveDto.setDescription(objective.getDescription());
        objectiveDto.setDuration(objective.getDuration());

        if (objective.getWorkplan() != null) {
            objectiveDto.setWorkplanId(objective.getWorkplan().getId());
        }

        objectiveDto.setTaskIds(objective.getTasks().stream().map(Task::getId).collect(Collectors.toSet()));

        return objectiveDto;
    }

    public static Objective convertToEntity(ObjectiveDto objectiveDto) {
        Objective objective = new Objective();
        objective.setName(objectiveDto.getName());
        objective.setStartDate(objectiveDto.getStartDate());
        objective.setEndDate(objectiveDto.getEndDate());
        objective.setDescription(objectiveDto.getDescription());
        objective.setDuration(objectiveDto.getDuration());

        if (objectiveDto.getWorkplanId() != null) {
            Workplan workplan = new Workplan();
            workplan.setId(objectiveDto.getWorkplanId());
            objective.setWorkplan(workplan);
        }

        if (objectiveDto.getTaskIds() != null) {
            Set<Task> tasks = objectiveDto.getTaskIds().stream().map(id -> {
                Task task = new Task();
                task.setId(id);
                return task;
            }).collect(Collectors.toSet());
            objective.setTasks(tasks);
        }

        return objective;
    }
}
