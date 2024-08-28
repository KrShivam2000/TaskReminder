package com.reminder.task.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReminderRequestDto {

    Long reminderId;
    String message;
    Long taskId;
    LocalDateTime reminderDate;

}