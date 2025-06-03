package com.tfg.taskmanager.service;

import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByProject(Project project) {
        return taskRepository.findByProject(project);
    }

    public List<Task> getTasksByAssignee(User assignee) {
        return taskRepository.findByAssignee(assignee);
    }

    public Optional<Task> getById(UUID id) {
        return taskRepository.findById(id);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}

