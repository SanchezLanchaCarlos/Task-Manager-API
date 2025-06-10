package com.tfg.taskmanager.controller;

import com.tfg.taskmanager.dto.ProjectDTO;
import com.tfg.taskmanager.dto.ProjectMemberDTO;
import com.tfg.taskmanager.mapper.ProjectMapper;
import com.tfg.taskmanager.mapper.ProjectMemberMapper;
import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.ProjectMember;
import com.tfg.taskmanager.model.Role;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.service.ProjectMemberService;
import com.tfg.taskmanager.service.ProjectService;
import com.tfg.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Projects", description = "Gestión de proyectos")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectMemberService projectMemberService;

    @GetMapping
    @Operation(summary = "Obtiene todos los proyectos")
    public List<ProjectDTO> all() {
        return projectService.getAllProjects()
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un proyecto por su ID")
    public ResponseEntity<ProjectDTO> getById(@PathVariable UUID id) {
        return projectService.getById(id)
                .map(ProjectMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Obtiene un proyecto por su nombre")
    public List<ProjectDTO> getByName(@PathVariable String name) {
        return projectService.getByName(name)
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo proyecto")
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO dto) {
        Optional<User> owner = userService.getById(dto.ownerId());
        if (owner.isEmpty()) return ResponseEntity.badRequest().build();

        Project saved = projectService.save(ProjectMapper.toEntity(dto, owner.get()));
        projectMemberService.save(new ProjectMember(null, owner.get(), saved, Role.OWNER));
        return ResponseEntity.ok(ProjectMapper.toDTO(saved));
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Obtiene los proyectos de un usuario")
    public List<ProjectDTO> getByOwner(@PathVariable UUID ownerId) {
        return userService.getById(ownerId)
                .map(projectService::getProjectsByOwner)
                .orElse(List.of())
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un proyecto por su ID")
    public void delete(@PathVariable UUID id) {
        projectService.deleteById(id);
    }
}

