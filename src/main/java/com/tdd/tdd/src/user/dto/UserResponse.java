package com.tdd.tdd.src.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private Long userIdx;
    private String email;
}
