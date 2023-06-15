package com.javarush.ivanilov.taskmanager.services;

import com.javarush.ivanilov.taskmanager.domain.Task;
import com.javarush.ivanilov.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository repo;

    public Task create(Task entity) {
        return repo.save(entity);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Task update(Task entity) {
        return repo.save(entity);
    }

    public Optional<Task> findById(Integer id) {
        return repo.findById(id);
    }

    public Page<Task> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
