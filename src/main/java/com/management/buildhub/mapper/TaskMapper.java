package com.management.buildhub.mapper;

import com.management.buildhub.dtos.TaskDto;
import com.management.buildhub.models.Crew;
import com.management.buildhub.models.Objective;
import com.management.buildhub.models.Task;

import java.util.Set;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setStartDate(task.getStartDate());
        taskDto.setEndDate(task.getEndDate());
        taskDto.setDescription(task.getDescription());
        taskDto.setDuration(task.getDuration());
        taskDto.setResources(task.getResources());

        taskDto.setObjectiveIds(task.getObjectives().stream().map(Objective::getId).collect(Collectors.toSet()));
        taskDto.setCrewIds(task.getCrews().stream().map(Crew::getId).collect(Collectors.toSet()));

        return taskDto;
    }

    public static Task convertToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setStartDate(taskDto.getStartDate());
        task.setEndDate(taskDto.getEndDate());
        task.setDescription(taskDto.getDescription());
        task.setDuration(taskDto.getDuration());
        task.setResources(taskDto.getResources());

        if (taskDto.getObjectiveIds() != null) {
            Set<Objective> objectives = taskDto.getObjectiveIds().stream().map(id -> {
                Objective objective = new Objective();
                objective.setId(id);
                return objective;
            }).collect(Collectors.toSet());
            task.setObjectives(objectives);
        }

        if (taskDto.getCrewIds() != null) {
            Set<Crew> crews = taskDto.getCrewIds().stream().map(id -> {
                Crew crew = new Crew();
                crew.setId(id);
                return crew;
            }).collect(Collectors.toSet());
            task.setCrews(crews);
        }

        return task;
    }
}
