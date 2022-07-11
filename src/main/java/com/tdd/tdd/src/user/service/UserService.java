package com.tdd.tdd.src.user.service;

import com.tdd.tdd.src.user.*;
import com.tdd.tdd.src.user.dto.SignUpRequest;
import com.tdd.tdd.src.user.dto.UserListResponseDTO;
import com.tdd.tdd.src.user.dto.UserResponse;
import com.tdd.tdd.src.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse signUp(SignUpRequest signUpRequest) {
        User user = User.builder()
                .userIdx(signUpRequest.getUserIdx())
                .email(signUpRequest.getEmail())
                .address(signUpRequest.getAddress())
                .build();
        User save = userRepository.save(user);

        return UserResponse.builder()
                .userIdx(save.getUserIdx())
                .email(save.getEmail())
                .build();
    }

    public UserListResponseDTO findAll() {
        List<User> all = userRepository.findAll();
        return UserListResponseDTO.builder()
                .userList(all)
                .build();
    }
}
