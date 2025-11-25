package com.taskflow.taskflow_bff_service.infrastructure.client;




import com.taskflow.taskflow_bff_service.business.dto.in.TaskDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.TaskDTOResponse;
import com.taskflow.taskflow_bff_service.infrastructure.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks", url = "${tasks.url}")
public interface TasksClient {


    @PostMapping
    TaskDTOResponse createTask(@RequestBody TaskDTORequest taskDTO, @RequestHeader("Authorization") String token);

    @GetMapping("/task-between-dates")
    List<TaskDTOResponse> getTasksBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TaskDTOResponse> getTasksByUserEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String taskId, @RequestHeader("Authorization") String token);

    @PatchMapping("/notification-status")
    TaskDTOResponse updateNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                             @RequestParam("taskId") String taskId,
                                             @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTOResponse updateTask(@RequestBody TaskDTORequest taskDTO,
                               @RequestParam("id") String id,
                               @RequestHeader("Authorization") String token);
}


