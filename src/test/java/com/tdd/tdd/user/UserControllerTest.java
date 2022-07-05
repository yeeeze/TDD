package com.tdd.tdd.user;

import com.google.gson.Gson;
import com.tdd.tdd.src.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    // http 호출을 위한 라이브러리
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("유저 등록 성공")
    @Test
    void signUpSuccess() throws Exception {
        SignUpRequest request = signUpReq();
        UserResponse response = userResponse();

        doReturn(response).when(userService)
                .signUp(any(SignUpRequest.class));

        // when
        // Spring 내부에서 Json String을 객체로 변환 (객체를 문자열로 변환해주는 Gson().toJson())
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        // API 호출 결과 201 Response와 응답결과 검증 (jsonPath를 이용해 해당 json 값이 존재하는지 확인)
        MvcResult mvcResult = resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("email", response.getEmail()).exists())
                .andExpect(jsonPath("userIdx", response.getUserIdx()).exists())
                .andReturn();
    }

    private SignUpRequest signUpReq() {
        return SignUpRequest.builder()
                .userIdx(1L)
                .email("test@test.test")
                .build();
    }

    private UserResponse userResponse() {
        return UserResponse.builder()
                .userIdx(1L)
                .email("test@test.test")
                .build();
    }

    @DisplayName("사용자 전체 목록 조회")
    @Test
    void getUserList() throws Exception {
        // given : service의 findAll에 대한 Stub이 필요하다.
        doReturn(userList()).when(userService)
                .findAll();

        // when : controller에서 호출하는 http 메소드를 GET으로 호출 (mockMVC.perform(MockMvcRequestBuilders.()))
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
        );

        // then
        // HTTP Status가 OK이며, 주어진 데이터가 올바른지 검증
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        // Json -> 객체 변환
        UserListResponseDTO response = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), UserListResponseDTO.class);
        assertThat(response.getUserList().size()).isEqualTo(5);
    }

    private UserListResponseDTO userList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.add(new User());
        }
        return UserListResponseDTO.builder()
                .userList(userList)
                .build();
    }
}
