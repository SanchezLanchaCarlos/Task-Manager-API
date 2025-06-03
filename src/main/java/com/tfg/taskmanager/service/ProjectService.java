package com.tfg.taskmanager.service;

import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByOwner(User owner) {
        return projectRepository.findByOwner(owner);
    }

    public Optional<Project> getById(UUID id) {
        return projectRepository.findById(id);
    }

    public List<Project> getByName(String name) {
        return projectRepository.findByName(name);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }
}
