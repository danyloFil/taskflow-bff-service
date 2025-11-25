package com.taskflow.taskflow_bff_service.business;


import com.taskflow.taskflow_bff_service.business.dto.in.TaskDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.TaskDTOResponse;
import com.taskflow.taskflow_bff_service.infrastructure.client.TasksClient;
import com.taskflow.taskflow_bff_service.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TasksClient tasksClient;


    public TaskDTOResponse createTask(TaskDTORequest taskDTO, String token) {
        return  tasksClient.createTask(taskDTO, token);
    }

    public List<TaskDTOResponse> findByEventDateBetween
            (LocalDateTime startDate, LocalDateTime endDate, String token) {
        return tasksClient.getTasksBetweenDates(startDate, endDate, token);
    }

    public List<TaskDTOResponse> findByUserEmail(String token) {
        return tasksClient.getTasksByUserEmail(token);
    }

    public void deleteTaskById(String taskId, String token) {
        tasksClient.deleteTaskById(taskId, token);
    }


    public TaskDTOResponse updateStatusNotification(NotificationStatusEnum status, String taskId, String token) {
        return tasksClient.updateNotificationStatus(status, taskId, token);
    }

    public TaskDTOResponse updateTask(TaskDTORequest dto, String taskId, String token) {
        return tasksClient.updateTask(dto, taskId, token);
    }

}


