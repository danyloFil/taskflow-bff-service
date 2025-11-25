package com.taskflow.taskflow_bff_service.controller;


import com.taskflow.taskflow_bff_service.business.UserService;
import com.taskflow.taskflow_bff_service.business.dto.in.AddressDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.LoginDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.PhoneDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.in.UserDTORequest;
import com.taskflow.taskflow_bff_service.business.dto.out.AddressDTOResponse;
import com.taskflow.taskflow_bff_service.business.dto.out.PhoneDTOResponse;
import com.taskflow.taskflow_bff_service.business.dto.out.UserDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name ="user", description = "Endpoints for managing users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details")
    @ApiResponse(responseCode = "200", description = "User created successfully")
    @ApiResponse(responseCode = "409", description = "User already exists or invalid data provided")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<UserDTOResponse> salveUserDTO(@RequestBody UserDTORequest userDTO){
        return ResponseEntity.ok(userService.createUser(userDTO));

    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logins a user with the provided credentials")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public String login(@RequestBody LoginDTORequest userDTO) {
        return userService.userLogin(userDTO);
    }

    @GetMapping
    @Operation(summary = "Get user by email", description = "Retrieves user details by email")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<UserDTOResponse> getUserByEmail(@RequestParam("email") String email,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(userService.getUserByEmail( email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email", description = "Deletes a user by their email")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        userService.removeUserByEmail(email, token);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    @Operation(summary = "Update user profile", description = "Updates the profile of the user")
    @ApiResponse(responseCode = "200", description = "User profile updated successfully")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<UserDTOResponse> updateUserProfile(@RequestBody UserDTORequest userDTO,
                                                             @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.updateUserProfile(userDTO , token));
    }

    @PutMapping("/address")
    @Operation(summary = "Update user address", description = "Updates the address of the user")
    @ApiResponse(responseCode = "200", description = "User address updated successfully")
    @ApiResponse(responseCode = "403", description = "Address not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<AddressDTOResponse> updateAddress(@RequestBody AddressDTORequest addressDTO,
                                                            @RequestParam("id") Long id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){

        return  ResponseEntity.ok(userService.updateAddress(id, addressDTO, token));
    }

    @PutMapping("/phoneNumber")
    @Operation(summary = "Update user phone number", description = "Updates the phone number of the user")
    @ApiResponse(responseCode = "200", description = "User phone number updated successfully")
    @ApiResponse(responseCode = "403", description = "Phone number not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<PhoneDTOResponse> updatePhoneNumber(@RequestBody PhoneDTORequest phoneDTO,
                                                              @RequestParam("id") Long id,
                                                              @RequestHeader(name = "Authorization", required = false) String token){

        return  ResponseEntity.ok(userService.updatePhoneNumber(id, phoneDTO, token));
    }

    @PostMapping("/createAddress")
    @Operation(summary = "Create user address", description = "Creates a new address for the user")
    @ApiResponse(responseCode = "200", description = "User address created successfully")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<AddressDTOResponse> createAddress(@RequestBody AddressDTORequest addressDTO,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
           return ResponseEntity.ok(userService.createAddress(token, addressDTO));

    }

    @PostMapping("createPhoneNumber")
    @Operation(summary = "Create user phone number", description = "Creates a new phone number for the user")
    @ApiResponse(responseCode = "200", description = "User phone number created successfully")
    @ApiResponse(responseCode = "403", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<PhoneDTOResponse> createPhoneNumber(@RequestBody PhoneDTORequest phoneDTO,
                                                              @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(userService.createPhoneNumber(token, phoneDTO));
        
    }

}
