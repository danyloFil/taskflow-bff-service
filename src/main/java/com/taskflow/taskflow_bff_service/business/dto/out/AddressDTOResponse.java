package com.taskflow.taskflow_bff_service.business.dto.out;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private String street;
    private String addressLine2;
    private Long houseNumber;
    private String city;
    private String county;
    private String eirCode;


}
