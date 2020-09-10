package com.youxu.wedding;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.youxu.wedding.mapper")
class WeddingApplicationTests {



    @Test
    void contextLoads() {

    }

}
