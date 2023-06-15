package com.javarush.ivanilov.taskmanager.repositories;

import com.javarush.ivanilov.taskmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
