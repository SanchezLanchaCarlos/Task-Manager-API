package com.tfg.taskmanager.repository;

import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.ProjectMember;
import com.tfg.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, UUID> {
    List<ProjectMember> findByProject(Project project);
    List<ProjectMember> findByUser(User user);
    Optional<ProjectMember> findByProjectAndUser(Project project, User user);
}