package com.reminder.task.entity;

import com.reminder.task.dto.ReminderRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reminder_date", nullable = false)
    private LocalDateTime reminderDate;

    @Column(columnDefinition = "TEXT")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ReminderRequestDto toDto(){
        ReminderRequestDto dto = new ReminderRequestDto();
        dto.setReminderId(this.getId());
        dto.setTaskId(this.getTask().getId());
        dto.setReminderDate(this.getReminderDate());
        dto.setMessage(this.getMessage());
        return dto;
    }
}