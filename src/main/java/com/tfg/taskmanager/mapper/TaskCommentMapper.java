package com.tfg.taskmanager.mapper;

import com.tfg.taskmanager.dto.TaskCommentDTO;
import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.TaskComment;
import com.tfg.taskmanager.model.User;

public class TaskCommentMapper {

    public static TaskCommentDTO toDTO(TaskComment comment) {
        return new TaskCommentDTO(
                comment.getId(),
                comment.getTask().getId(),
                comment.getAuthor().getId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }

    public static TaskComment toEntity(TaskCommentDTO dto, Task task, User user) {
        TaskComment comment = new TaskComment();
        comment.setTask(task);
        comment.setAuthor(user);
        comment.setContent(dto.content());
        comment.setCreatedAt(dto.createdAt());
        return comment;
    }
}
