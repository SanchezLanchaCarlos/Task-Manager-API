package com.tfg.taskmanager.service;

import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.TaskComment;
import com.tfg.taskmanager.repository.TaskCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskCommentService {

    private final TaskCommentRepository commentRepository;

    public List<TaskComment> getCommentsByTask(Task task) {
        return commentRepository.findByTask(task);
    }

    public Optional<TaskComment> getById(UUID id) {
        return commentRepository.findById(id);
    }

    public TaskComment save(TaskComment comment) {
        return commentRepository.save(comment);
    }

    public void deleteById(UUID id) {
        commentRepository.deleteById(id);
    }
}

