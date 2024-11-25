package TestTask.demo.controller;

import TestTask.demo.dto.UserDto;
import TestTask.demo.model.UserModel;
import TestTask.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDto> createUserAccount(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createAccount(userDto));

    }

    private UserDto createAccount(UserDto userDto) {
        var user = new UserModel();
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());

        var createdUser = userService.createNewAccount(user);
        return UserDto.builder()
                .username(createdUser.getUsername())
                .password(createdUser.getPassword())
                .build();
    }




}
