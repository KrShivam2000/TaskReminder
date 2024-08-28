package com.reminder.task.controller;

import com.reminder.task.dto.ReminderRequestDto;
import com.reminder.task.entity.Reminder;

import com.reminder.task.service.ReminderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public ResponseEntity<ReminderRequestDto> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        log.info("reminder with id: {} created successfully ", createdReminder.getId());
        return new ResponseEntity<>(createdReminder.toDto(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderRequestDto> updateReminder(@PathVariable Long id, @RequestBody Reminder reminder) {
        Reminder updatedReminder = reminderService.updateReminder(id, reminder);
        if (updatedReminder != null) {
            log.info("reminder id: {} updated",id);
            return new ResponseEntity<>(updatedReminder.toDto(), HttpStatus.OK);
        } else {
            log.warn("reminder id: {} not found",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        Reminder reminder = reminderService.getReminderById(id);
        if (reminder != null){
            reminderService.deleteReminder(id);
            log.info("reminder with id: {} deleted successfully ", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.warn("reminder with id: {} not present", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderRequestDto> getReminderById(@PathVariable Long id) {
        log.info("fetching reminder with id: {}", id);
        Reminder reminder = reminderService.getReminderById(id);
        if (reminder != null) {
            log.info("reminder with id: {} found ", id);
            return new ResponseEntity<>(reminder.toDto(), HttpStatus.OK);
        } else {
            log.warn("reminder with id: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ReminderRequestDto>> getRemindersByTaskId(@PathVariable Long taskId) {
        log.info("fetching reminders for task id: {}", taskId);
        List<Reminder> reminders = reminderService.getRemindersByTaskId(taskId);
        log.info("total reminders found for task id {}: {}", taskId, reminders.size());
        List<ReminderRequestDto> reminderRequestDtoList= reminders.stream().map(Reminder::toDto).toList();
        return new ResponseEntity<>(reminderRequestDtoList, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReminderRequestDto>> getAllTasks() {
        List<Reminder> reminders = reminderService.getAllTasks();
        List<ReminderRequestDto> reminderRequestDtoList = reminders.stream()
                .map(Reminder::toDto)
                .toList();
        log.info("total reminders found: {}",reminderRequestDtoList.size());
        return new ResponseEntity<>(reminderRequestDtoList, HttpStatus.OK);
    }
}