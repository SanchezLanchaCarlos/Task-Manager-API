package com.tfg.taskmanager.repository;

import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByOwner(User owner);
    List<Project> findByName(String Name);
}
