package com.tfg.taskmanager.mapper;

import com.tfg.taskmanager.dto.TaskDTO;
import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.User;

public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getAssignee() != null ? task.getAssignee().getId() : null,
                task.getProject() != null ? task.getProject().getId() : null,
                task.getCreatedAt()
        );
    }

    public static Task toEntity(TaskDTO dto, User assignee, Project project) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());
        task.setAssignee(assignee);
        task.setProject(project);
        task.setCreatedAt(dto.createdAt());
        return task;
    }
}
