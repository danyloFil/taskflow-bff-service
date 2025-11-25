package com.taskflow.taskflow_bff_service.controller;


import com.taskflow.taskflow_bff_service.business.TasksService;
import com.taskflow.taskflow_bff_service.business.dto.in.TaskDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.TaskDTOResponse;
import com.taskflow.taskflow_bff_service.infrastructure.enums.NotificationStatusEnum;
import com.taskflow.taskflow_bff_service.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name ="Tasks", description = "Endpoints for managing user tasks")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    @Operation(summary = "Create a new task", description = "Creates a new task for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Task created successfully")
    @ApiResponse(responseCode = "401", description = "User not authenticated")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity <TaskDTOResponse> createTask(@RequestBody TaskDTORequest taskDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.createTask(taskDTO, token));
    }

    @GetMapping("/task-between-dates")
    @Operation(summary = "Get tasks between dates", description = "Retrieves tasks scheduled between the specified start and end dates for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    @ApiResponse(responseCode = "401", description = "User not authenticated")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<TaskDTOResponse>> getTasksBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tasksService.findByEventDateBetween(startDate, endDate, token));
    }

    @GetMapping
    @Operation(summary = "Get tasks by user email", description = "Retrieves all tasks for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    @ApiResponse(responseCode = "401", description = "User not authenticated")
    @ApiResponse(responseCode = "403", description = "Email not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<TaskDTOResponse>> getTasksByUserEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.findByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete task by ID", description = "Deletes a task by its ID for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Task deleted successfully")
    @ApiResponse(responseCode = "401", description = "User not authenticated")
    @ApiResponse(responseCode = "403", description = "Task id not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String taskId, @RequestHeader(name = "Authorization", required = false) String token) {
        tasksService.deleteTaskById(taskId, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/notification-status")
    @Operation(summary = "Update notification status", description = "Updates the notification status of a specific task for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Notification status updated successfully")
    @ApiResponse(responseCode = "401", description = "User not authenticated")
    @ApiResponse(responseCode = "403", description = "Task id not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TaskDTOResponse> updateNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                                                    @RequestParam("taskId") String taskId,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
       return  ResponseEntity.ok(tasksService.updateStatusNotification(status, taskId, token));

    }

    @PutMapping
    @Operation(summary = "Update task", description = "Updates the details of a specific task for the authenticated user")
    @ApiResponse(responseCode = "200", description = "Task updated successfully")
    @ApiResponse(responseCode = "401", description = "User not authenticated")
    @ApiResponse(responseCode = "403", description = "Task id not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<TaskDTOResponse> updateTask(@RequestBody TaskDTORequest taskDTO,
                                                      @RequestParam("id") String id,
                                                      @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tasksService.updateTask(taskDTO, id, token));
    }


}
