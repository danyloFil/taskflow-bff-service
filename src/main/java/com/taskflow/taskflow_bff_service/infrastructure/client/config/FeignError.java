package com.taskflow.taskflow_bff_service.infrastructure.client.config;

import com.taskflow.taskflow_bff_service.infrastructure.exception.BusinessException;
import com.taskflow.taskflow_bff_service.infrastructure.exception.ConflictException;
import com.taskflow.taskflow_bff_service.infrastructure.exception.ResourceNotFoundException;
import com.taskflow.taskflow_bff_service.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 409 -> new ConflictException("Conflict: User or Resource already exists.");
            case 403 -> new ResourceNotFoundException("Forbidden: You do not have access to this resource.");
            case 401 -> new UnauthorizedException("Unauthorized: You are not authorized to perform this action.");
            case 404 -> new ResourceNotFoundException("Not Found: The requested resource could not be found.");
            default -> new BusinessException("Server error: An unexpected problem occurred.");
        };
    }
}
