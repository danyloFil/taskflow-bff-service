package com.taskflow.taskflow_bff_service.infrastructure.client;


import com.taskflow.taskflow_bff_service.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email", url = "${notification.url}")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TaskDTOResponse taskDTO);

}


