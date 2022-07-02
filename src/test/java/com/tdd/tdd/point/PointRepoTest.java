package com.tdd.tdd.point;

import com.tdd.tdd.src.point.PointRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class PointRepoTest {

    @Autowired
    private PointRepository pointRepository;

    @Test
    public void pointRepo가Null이아님() {
        assertThat(pointRepository).isNotNull();
    }
}
