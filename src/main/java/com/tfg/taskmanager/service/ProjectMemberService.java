package com.tfg.taskmanager.service;

import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.ProjectMember;
import com.tfg.taskmanager.model.User;
import com.tfg.taskmanager.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMember> getMembersByProject(Project project) {
        return projectMemberRepository.findByProject(project);
    }

    public List<ProjectMember> getMembershipsByUser(User user) {
        return projectMemberRepository.findByUser(user);
    }

    public Optional<ProjectMember> getByProjectAndUser(Project project, User user) {
        return projectMemberRepository.findByProjectAndUser(project, user);
    }

    public ProjectMember save(ProjectMember member) {
        return projectMemberRepository.save(member);
    }

    public void deleteById(UUID id) {
        projectMemberRepository.deleteById(id);
    }
}
