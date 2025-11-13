package com.taskflow.taskflow_bff_service.business;


import com.taskflow.taskflow_bff_service.business.dto.in.AddressDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.LoginRequest;
import com.taskflow.taskflow_bff_service.business.dto.in.PhoneDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.UserDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.AddressDTOResponse;
import com.taskflow.taskflow_bff_service.business.dto.out.PhoneDTOResponse;
import com.taskflow.taskflow_bff_service.business.dto.out.UserDTOResponse;
import com.taskflow.taskflow_bff_service.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;


    public UserDTOResponse createUser(UserDTORequest userDTO){
        return userClient.salveUserDTO(userDTO);
    }


    public String userlogin(LoginRequest loginRequest) {
        return userClient.login(loginRequest);
    }

    public UserDTOResponse getUserByEmail(String email, String token) {
        return userClient.getUserByEmail(email, token);

    }

    public void removeUserByEmail(String email, String token) {
        userClient.deleteUserByEmail(email, token);
    }


    public UserDTOResponse updateUserProfile(UserDTORequest userDTO, String token){
        return userClient.updateUserProfile(userDTO, token);

    }

    public AddressDTOResponse updateAddress(Long addressID, AddressDTORequest addressDTO, String token){
        return userClient.updateAddress(addressDTO, addressID, token);
    }

    public PhoneDTOResponse updatePhoneNumber(Long phoneNumberID, PhoneDTORequest phoneDTO, String token){
        return userClient.updatePhoneNumber(phoneDTO, phoneNumberID, token);

    }

    public AddressDTOResponse createAddress(String token, AddressDTORequest addressDTO){
        return userClient.createAddress(addressDTO, token);}


    public PhoneDTOResponse createPhoneNumber(String token, PhoneDTORequest phoneDTO){
        return userClient.createPhoneNumber(phoneDTO, token);
    }

}
