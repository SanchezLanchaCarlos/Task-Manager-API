package com.tfg.taskmanager.controller;

import com.tfg.taskmanager.dto.TaskDTO;
import com.tfg.taskmanager.mapper.ProjectMapper;
import com.tfg.taskmanager.mapper.TaskMapper;
import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.service.ProjectService;
import com.tfg.taskmanager.service.TaskService;
import com.tfg.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Tasks", description = "Gestión de tareas")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "Obtiene todas las tareas")
    public List<TaskDTO> all() {
        return taskService.getAllTasks()
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una tarea por su ID")
    public ResponseEntity<TaskDTO> getById(@PathVariable UUID id) {
        return taskService.getById(id)
                .map(TaskMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crea una nueva tarea")
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO dto) {
        Optional<User> assignee = userService.getById(dto.assigneeId());
        Optional<Project> project = projectService.getById(dto.projectId());

        if (assignee.isEmpty() || project.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Task saved = taskService.save(TaskMapper.toEntity(dto, assignee.get(), project.get()));
        return ResponseEntity.ok(TaskMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una tarea por su ID")
    public void delete(@PathVariable UUID id) {
        taskService.deleteById(id);
    }

    @GetMapping("/project/{projectId}")
    @Operation(summary = "Obtiene todas las tareas de un proyecto")
    public List<TaskDTO> getByProject(@PathVariable UUID projectId) {
        return projectService.getById(projectId)
                .map(taskService::getTasksByProject)
                .orElse(List.of())
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtiene todas las tareas de un usuario")
    public List<TaskDTO> getByUser(@PathVariable UUID userId) {
        return userService.getById(userId)
                .map(taskService::getTasksByAssignee)
                .orElse(List.of())
                .stream()
                .map(TaskMapper::toDTO)
                .toList();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una tarea por su ID")
    public TaskDTO update(@PathVariable UUID id, @RequestBody TaskDTO dto) {
        Task task = taskService.getById(id).orElseThrow(RuntimeException::new);
        User user = userService.getById(dto.assigneeId()).orElseThrow(RuntimeException::new);
        Project project = projectService.getById(dto.projectId()).orElseThrow(RuntimeException::new);
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setAssignee(user);
        task.setDueDate(dto.dueDate());
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setProject(project);
        return TaskMapper.toDTO(taskService.save(task));
    }
}


