package com.tfg.taskmanager.mapper;

import com.tfg.taskmanager.dto.ProjectMemberDTO;
import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.ProjectMember;
import com.tfg.taskmanager.model.User;

public class ProjectMemberMapper {

    public static ProjectMemberDTO toDTO(ProjectMember member) {
        return new ProjectMemberDTO(
                member.getId(),
                member.getUser().getId(),
                member.getProject().getId(),
                member.getRole()
        );
    }

    public static ProjectMember toEntity(ProjectMemberDTO dto, User user, Project project) {
        ProjectMember member = new ProjectMember();
        member.setUser(user);
        member.setProject(project);
        member.setRole(dto.role());
        return member;
    }
}
