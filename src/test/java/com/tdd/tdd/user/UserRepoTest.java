package com.tdd.tdd.user;

import com.tdd.tdd.src.user.User;
import com.tdd.tdd.src.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 인메모리 DB인 H2를 기반으로 테스트용 DB를 구축하며, 테스트가 끝나면 트랜잭션 롤백을 해준다.
// Repo 계층은 실제 DB와 통신 없이 단순 모킹하는 것은 의미가 없으므로
// 직접 DB와 통신하는 @DataJpaTest를 사용한다.
@DataJpaTest
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("유저 등록")
    @Test
    public void create() {
        final User user = User.builder()
                .userIdx(1L)
                .email("test@test.com")
                .name("테스트")
                .address("서울시 테스트")
                .build();

        User user1 = userRepository.save(user);

        assertThat(user1.getName()).isEqualTo("테스트");
    }

    @DisplayName("유저 전체 리스트 조회")
    @Test
    void userList() {
        userRepository.save(User.builder()
                .userIdx(1L)
                .name("test")
                .email("test@test.com")
                .build());

        List<User> userList = userRepository.findAll();

        assertThat(userList.size()).isEqualTo(1);
        assertThat(userList.get(0).getName()).isEqualTo("test");
    }

    //요청 : 유저 id, 금액
    @DisplayName("포인트 지급")
    @Test
    public void point() {
        Long userIdx = 1L;
        int point = 10000;

        final User user = User.builder()
                .userIdx(userIdx)
                .email("test@test.com")
                .name("테스트")
                .address("서울시 테스트")
                .point(point)
                .build();

        // save()는 이미 있는 데이터의 경우 update
        User user1 = userRepository.save(user);

        assertThat(user1.getPoint()).isEqualTo(10000);
    }
}
