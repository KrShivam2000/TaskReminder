package com.reminder.task.dao;

import com.reminder.task.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {

    List<Reminder> findByTaskId(Long taskId);
}
