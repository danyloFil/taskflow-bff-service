package com.taskflow.taskflow_bff_service.business.dto.in;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTORequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressDTORequest> addressDTOS;
    private List<PhoneDTORequest> phoneDTOS;




}
