package com.reminder.task.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequestDto {

    Long id;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
