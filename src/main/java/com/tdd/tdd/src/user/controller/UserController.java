package com.tdd.tdd.src.user.controller;

import com.tdd.tdd.src.user.dto.SignUpRequest;
import com.tdd.tdd.src.user.dto.UserResponse;
import com.tdd.tdd.src.user.dto.UserListResponseDTO;
import com.tdd.tdd.src.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signUp")
    public ResponseEntity<UserResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.signUp(request));
    }

    @GetMapping("/users")
    public ResponseEntity<UserListResponseDTO> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
