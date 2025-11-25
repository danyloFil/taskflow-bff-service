package com.taskflow.taskflow_bff_service.business;


import com.taskflow.taskflow_bff_service.business.dto.out.TaskDTOResponse;
import com.taskflow.taskflow_bff_service.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void sendEmail(TaskDTOResponse taskDTO) {
        emailClient.sendEmail(taskDTO);
    }
}


