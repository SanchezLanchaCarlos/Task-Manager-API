package com.tfg.taskmanager.repository;


import com.tfg.taskmanager.model.Project;
import com.tfg.taskmanager.model.Task;
import com.tfg.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByProject(Project project);
    List<Task> findByAssignee(User user);
}
