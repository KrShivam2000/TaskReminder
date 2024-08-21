package com.reminder.task.service.impl;

import com.reminder.task.dao.ReminderRepository;
import com.reminder.task.entity.Reminder;
import com.reminder.task.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Override
    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @Override
    public Reminder updateReminder(Long id, Reminder reminder) {
        Optional<Reminder> existingReminder = reminderRepository.findById(id);
        if (existingReminder.isPresent()) {
            Reminder updatedReminder = existingReminder.get();
            updatedReminder.setReminderDate(reminder.getReminderDate());
            updatedReminder.setMessage(reminder.getMessage());
            return reminderRepository.save(updatedReminder);
        }
        return null; // Or throw an exception
    }

    @Override
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public Reminder getReminderById(Long id) {
        return reminderRepository.findById(id).orElse(null); // Or throw an exception
    }

    @Override
    public List<Reminder> getRemindersByTaskId(Long taskId) {
        return reminderRepository.findByTaskId(taskId);
    }
}
