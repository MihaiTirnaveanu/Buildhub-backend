package com.management.buildhub.services;

import com.management.buildhub.dtos.TaskDto;
import com.management.buildhub.mapper.TaskMapper;
import com.management.buildhub.models.Task;
import com.management.buildhub.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<TaskDto> getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(TaskMapper::convertToDto);
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = TaskMapper.convertToEntity(taskDto);
        Task createdTask = taskRepository.save(task);
        return TaskMapper.convertToDto(createdTask);
    }

    public TaskDto updateTask(TaskDto updatedTaskDto) {
        Long id = updatedTaskDto.getId();
        Optional<Task> existingTaskOptional = taskRepository.findById(id);

        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            Task updatedTask = TaskMapper.convertToEntity(updatedTaskDto);
            existingTask.setName(updatedTask.getName());
            existingTask.setStartDate(updatedTask.getStartDate());
            existingTask.setEndDate(updatedTask.getEndDate());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDuration(updatedTask.getDuration());
            existingTask.setResources(updatedTask.getResources());
            Task savedTask = taskRepository.save(existingTask);
            return TaskMapper.convertToDto(savedTask);
        }

        return null;
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
