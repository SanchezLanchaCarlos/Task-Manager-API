package com.tfg.taskmanager.mapper;

import com.tfg.taskmanager.dto.ProjectDTO;
import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.User;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(project.getId(), project.getName(), project.getDescription(),
                project.getOwner() != null ? project.getOwner().getId() : null);
    }

    public static Project toEntity(ProjectDTO dto, User owner) {
        Project project = new Project();
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setOwner(owner);
        return project;
    }
}
