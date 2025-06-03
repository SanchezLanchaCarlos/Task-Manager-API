package com.tfg.taskmanager.controller;

import com.tfg.taskmanager.dto.TaskCommentDTO;
import com.tfg.taskmanager.mapper.TaskCommentMapper;
import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.TaskComment;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.service.TaskCommentService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Comments", description = "Gestión de comentarios de una tarea")
public class TaskCommentController {

    private final TaskCommentService commentService;
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Obtiene todos los comentarios de una tarea")
    public List<TaskCommentDTO> getByTask(@PathVariable UUID taskId) {
        return taskService.getById(taskId)
                .map(commentService::getCommentsByTask)
                .orElse(List.of())
                .stream()
                .map(TaskCommentMapper::toDTO)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo comentario")
    public ResponseEntity<TaskCommentDTO> create(@RequestBody TaskCommentDTO dto) {
        Optional<Task> task = taskService.getById(dto.taskId());
        Optional<User> user = userService.getById(dto.userId());

        if (task.isEmpty() || user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        TaskComment saved = commentService.save(TaskCommentMapper.toEntity(dto, task.get(), user.get()));
        return ResponseEntity.ok(TaskCommentMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un comentario por su ID")
    public void delete(@PathVariable UUID id) {
        commentService.deleteById(id);
    }
}


