package com.reminder.task.controller;

import com.reminder.task.entity.Reminder;

import com.reminder.task.service.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);

    @Autowired
    private ReminderService reminderService;

    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.createReminder(reminder);
        logger.info("Reminder created successfully with ID: {}", createdReminder.getId());
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable Long id, @RequestBody Reminder reminder) {
        Reminder updatedReminder = reminderService.updateReminder(id, reminder);
        if (updatedReminder != null) {
            return new ResponseEntity<>(updatedReminder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        logger.info("Reminder deleted successfully with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Long id) {
        logger.info("Fetching reminder with ID: {}", id);
        Reminder reminder = reminderService.getReminderById(id);
        if (reminder != null) {
            logger.info("Reminder found with ID: {}", id);
            return new ResponseEntity<>(reminder, HttpStatus.OK);
        } else {
            logger.warn("Reminder with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Reminder>> getRemindersByTaskId(@PathVariable Long taskId) {
        logger.info("Fetching reminders for task ID: {}", taskId);
        List<Reminder> reminders = reminderService.getRemindersByTaskId(taskId);
        logger.info("Total reminders found for task ID {}: {}", taskId, reminders.size());
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }
}
