package com.taskflow.taskflow_bff_service.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.taskflow_bff_service.infrastructure.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTORequest {


    private String taskName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;

}
