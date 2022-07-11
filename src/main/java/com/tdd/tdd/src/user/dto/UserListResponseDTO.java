package com.tdd.tdd.src.user.dto;

import com.tdd.tdd.src.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserListResponseDTO {

    private List<User> userList;

}
