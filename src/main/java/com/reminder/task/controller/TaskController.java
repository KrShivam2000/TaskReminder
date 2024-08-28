package com.reminder.task.controller;

import com.reminder.task.dto.TaskRequestDto;
import com.reminder.task.entity.Task;
import com.reminder.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskRequestDto> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        log.info("task created successfully with id: {}", createdTask.getId());
        return new ResponseEntity<>(createdTask.toDto(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskRequestDto> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        if (updatedTask != null) {
            log.info("task id: {} updated",id);
            return new ResponseEntity<>(updatedTask.toDto(), HttpStatus.OK);
        } else {
            log.warn("task id: {} not found",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            taskService.deleteTask(id);
            log.info("task with id: {} deleted successfully ", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.warn("task with id: {} not present", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRequestDto> getTaskById(@PathVariable Long id) {
        log.info("fetching task with id: {}", id);
        Task task = taskService.getTaskById(id);
        if (task != null) {
            log.info("task with id: {} found ", id);
            return new ResponseEntity<>(task.toDto(), HttpStatus.OK);
        } else {
            log.warn("task with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskRequestDto>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskRequestDto> taskRequestDtoList = tasks.stream()
                .map(Task::toDto)
                .toList();

        log.info("total tasks found: {}",taskRequestDtoList.size());
        return new ResponseEntity<>(taskRequestDtoList, HttpStatus.OK);
    }
}
