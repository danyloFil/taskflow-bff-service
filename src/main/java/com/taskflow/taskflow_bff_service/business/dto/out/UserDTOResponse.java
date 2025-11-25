package com.taskflow.taskflow_bff_service.business.dto.out;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressDTOResponse> addressDTOS;
    private List<PhoneDTOResponse> phoneDTOS;




}
