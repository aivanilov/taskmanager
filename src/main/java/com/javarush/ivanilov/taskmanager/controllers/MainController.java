package com.javarush.ivanilov.taskmanager.controllers;

import com.javarush.ivanilov.taskmanager.domain.Status;
import com.javarush.ivanilov.taskmanager.domain.Task;
import com.javarush.ivanilov.taskmanager.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    private final TaskService taskService;

    @GetMapping
    public ModelAndView getAllTasks() {
        return getModelAndViewForIndexPage(1);
    }

    private ModelAndView getModelAndViewForIndexPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        Page<Task> tasks = taskService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("numberOfTasks", tasks.getTotalElements());
        List<Integer> pages = new ArrayList<>();

        for (int i = 1; i <= tasks.getTotalPages(); i++) {
            pages.add(i);
        }

        modelAndView.addObject("pages", pages);
        return modelAndView;
    }

    @GetMapping("/{pageNumber}")
    public ModelAndView getPage(@PathVariable Integer pageNumber) {
        return getModelAndViewForIndexPage(pageNumber);
    }

    @PostMapping("/tasks/create")
    public ModelAndView createTask(@RequestParam String description,
                                   @RequestParam String status) {
        taskService.create(new Task(0, description, Status.valueOf(status)));
        return getAllTasks();
    }

    @GetMapping("/tasks/edit/{id}")
    public ModelAndView editTask(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("editTask");
        Optional<Task> optional = taskService.findById(id);
        optional.ifPresent(task -> modelAndView.addObject("task", task));
        return modelAndView;
    }

    @PostMapping("/tasks/edit")
    public ModelAndView editTask(@RequestParam String description,
                                 @RequestParam String status,
                                 @RequestParam Integer id) {
        ModelAndView modelAndView = new ModelAndView("message");
        Optional<Task> optionalTask = taskService.findById(id);
        String message;

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(description);
            task.setStatus(Status.valueOf(status));
            taskService.update(task);
            message = String.format("Task #%d has been updated successfully.", id);
            modelAndView.addObject("successMessage", message);
        } else {
            message = String.format("Error. Task #%d doesn't exist.", id);
            modelAndView.addObject("errorMessage", message);
        }

        return modelAndView;
    }

    @PostMapping("/tasks/delete/{id}")
    public ModelAndView deleteTask(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("message");
        taskService.deleteById(id);
        Optional<Task> optionalTask = taskService.findById(id);
        String message;

        if (optionalTask.isEmpty()) {
            message = String.format("Task #%d has been deleted successfully.", id);
            modelAndView.addObject("successMessage", message);
        } else {
            message = String.format("Error. Task #%d hasn't been deleted.", id);
            modelAndView.addObject("errorMessage", message);
        }

        return modelAndView;
    }
}
