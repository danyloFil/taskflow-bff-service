package com.taskflow.taskflow_bff_service.infrastructure.client;


import com.taskflow.taskflow_bff_service.business.dto.in.AddressDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.LoginDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.PhoneDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.UserDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.AddressDTOResponse;
import com.taskflow.taskflow_bff_service.business.dto.out.PhoneDTOResponse;
import com.taskflow.taskflow_bff_service.business.dto.out.UserDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user", url = "${user.url}")
public interface UserClient {

    @GetMapping("/user")
    UserDTOResponse getUserByEmail(@RequestParam("email") String email,
                                   @RequestHeader("Authorization") String token);

    @PostMapping
    UserDTOResponse salveUserDTO(@RequestBody UserDTORequest userDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest userDTO);


    @DeleteMapping("/{email}")
    void deleteUserByEmail(@PathVariable String email,
                           @RequestHeader("Authorization") String token);

    @PutMapping
    UserDTOResponse updateUserProfile(@RequestBody UserDTORequest userDTO,
                                      @RequestHeader("Authorization") String token);

    @PutMapping("/address")
    AddressDTOResponse updateAddress(@RequestBody AddressDTORequest addressDTO,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/phoneNumber")
    PhoneDTOResponse updatePhoneNumber(@RequestBody PhoneDTORequest phoneDTO,
                                       @RequestParam("id") Long id,
                                       @RequestHeader("Authorization") String token);

    @PostMapping("/createAddress")
    AddressDTOResponse createAddress(@RequestBody AddressDTORequest addressDTO,
                                     @RequestHeader("Authorization") String token);

    @PostMapping("createPhoneNumber")
    PhoneDTOResponse createPhoneNumber(@RequestBody PhoneDTORequest phoneDTO,
                                       @RequestHeader("Authorization") String token);

}


