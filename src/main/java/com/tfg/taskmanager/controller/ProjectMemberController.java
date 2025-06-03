package com.tfg.taskmanager.controller;

import com.tfg.taskmanager.dto.ProjectMemberDTO;
import com.tfg.taskmanager.mapper.ProjectMemberMapper;
import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.ProjectMember;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.service.ProjectMemberService;
import com.tfg.taskmanager.service.ProjectService;
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
@RequestMapping("/api/project-members")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Project Members", description = "Gestión de miembros de un proyecto")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;
    private final UserService userService;

    @GetMapping("/project/{projectId}")
    @Operation(summary = "Obtiene los miembros de un proyecto por su ID")
    public List<ProjectMemberDTO> getMembersByProject(@PathVariable UUID projectId) {
        return projectService.getById(projectId)
                .map(projectMemberService::getMembersByProject)
                .orElse(List.of())
                .stream()
                .map(ProjectMemberMapper::toDTO)
                .toList();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtiene los proyectos de un usuario por su ID")
    public List<ProjectMemberDTO> getMembershipsByUser(@PathVariable UUID userId) {
        return userService.getById(userId)
                .map(projectMemberService::getMembershipsByUser)
                .orElse(List.of())
                .stream()
                .map(ProjectMemberMapper::toDTO)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Agrega un nuevo miembro a un proyecto")
    public ResponseEntity<ProjectMemberDTO> addMember(@RequestBody ProjectMemberDTO dto) {
        Optional<User> user = userService.getById(dto.userId());
        Optional<Project> project = projectService.getById(dto.projectId());

        if (user.isEmpty() || project.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ProjectMember saved = projectMemberService.save(ProjectMemberMapper.toEntity(dto, user.get(), project.get()));
        return ResponseEntity.ok(ProjectMemberMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un miembro de un proyecto por su ID")
    public void removeMember(@PathVariable UUID id) {
        projectMemberService.deleteById(id);
    }
}

