package com.reminder.task.service;

import com.reminder.task.entity.Reminder;
import com.reminder.task.entity.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReminderService {

    Reminder createReminder(Reminder reminder);

    Reminder updateReminder(Long id, Reminder reminder);

    void deleteReminder(Long id);

    Reminder getReminderById(Long id);

    List<Reminder> getRemindersByTaskId(Long taskId);

    List<Reminder> getAllTasks();
}
