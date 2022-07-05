package com.tdd.tdd.src.user;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private Long userIdx;
    private String email;
    private String address;
}
