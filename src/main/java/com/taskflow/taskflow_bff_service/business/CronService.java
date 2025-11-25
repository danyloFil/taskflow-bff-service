package com.taskflow.taskflow_bff_service.business;

import com.taskflow.taskflow_bff_service.business.dto.in.LoginDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.TaskDTOResponse;
import com.taskflow.taskflow_bff_service.infrastructure.client.EmailClient;
import com.taskflow.taskflow_bff_service.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TasksService tasksService;
    private final EmailClient emailClient;
    private final UserService userService;

    @Value("${user.email}")
    private String userEmail;

    @Value("${user.password}")
    private String userPassword;

    @Scheduled(cron = "${cron.hour}")
    public void findTasksForNextHour() {
        String token = login(convertToLoginDTO());
        log.info("Cron job started to find tasks for the next hour.");
        LocalDateTime currentHour = LocalDateTime.now();
        LocalDateTime nextHourPlusFive = LocalDateTime.now().plusHours(1);

        List<TaskDTOResponse> taskList = tasksService.findByEventDateBetween(currentHour, nextHourPlusFive, token);
        log.info("Found {} tasks scheduled between {} and {}.", taskList.size(), currentHour, nextHourPlusFive);
        taskList.forEach(task -> {
            emailClient.sendEmail(task);
            log.info("Sent notification email for task ID: {}", task.getId());
            tasksService.updateStatusNotification(NotificationStatusEnum.NOTIFIED, task.getId(), token);
        });
        log.info("Cron job completed.");
    }

    public String login(LoginDTORequest dto) {
        return userService.userLogin(dto);
    }

    public LoginDTORequest convertToLoginDTO() {
        return LoginDTORequest.builder()
                .email(userEmail)
                .password(userPassword)
                .build();
    }
}
