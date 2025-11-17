package com.taskflow.taskflow_bff_service.business.dto.in;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTORequest {


    private Long phoneNumber;
    private Long areaCode;
}
