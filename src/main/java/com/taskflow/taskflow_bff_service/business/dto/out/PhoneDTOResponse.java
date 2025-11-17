package com.taskflow.taskflow_bff_service.business.dto.out;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTOResponse {

    private Long id;
    private Long phoneNumber;
    private Long areaCode;
}
