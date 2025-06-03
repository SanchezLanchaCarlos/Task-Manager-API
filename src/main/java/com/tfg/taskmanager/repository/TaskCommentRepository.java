package com.tfg.taskmanager.repository;

import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskCommentRepository extends JpaRepository<TaskComment, UUID> {
    List<TaskComment> findByTask(Task task);
}
