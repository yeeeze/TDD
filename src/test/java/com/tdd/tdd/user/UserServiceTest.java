package com.tdd.tdd.user;

import com.tdd.tdd.src.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    //@Spy : Mock하지 않은 메소드는 실제 메소드로 동작

    @DisplayName("회원 가입")
    @Test
    void signUp() {
        // given
        SignUpRequest request = signUpRequest();

        //repo에서 데이터가 넘어옴
        User user = User.builder()
                .userIdx(request.getUserIdx())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();

        // Mockito에서 제공하는 stub 메소드
        // 의존성이 있는 가짜 객체는 가짜 객체 (Mock Object)를 주입하여
        // 어떤 결과를 반환하라고 정해진 답변을 준비시켜야함
        doReturn(user).when(userRepository)
                .save(any(User.class));

        // when
        UserResponse response = userService.signUp(request);

        // then
        assertThat(response.getEmail()).isEqualTo(request.getEmail());

        // verify (Mock된 객체의 특정 메소드가 호출된 횟수 검증)
        verify(userRepository, times(1)).save(any(User.class));
    }

    private SignUpRequest signUpRequest() {
        return SignUpRequest.builder()
                .userIdx(1L)
                .email("test@test.com")
                .address("test시")
                .build();
    }

    @DisplayName("유저 전체 목록 조회")
    @Test
    void findAll() {
        // given
        doReturn(userList()).when(userRepository)
                .findAll();

        // when
        UserListResponseDTO all = userService.findAll();

        // then
        assertThat(all.getUserList().size()).isEqualTo(5);
    }

    private List<User> userList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(new User());
        }
        return userList;
    }
}
