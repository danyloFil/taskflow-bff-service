package com.taskflow.taskflow_bff_service.business.dto.in;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTORequest {


    private String street;
    private String addressLine2;
    private Long houseNumber;
    private String city;
    private String county;
    private String eirCode;


}
