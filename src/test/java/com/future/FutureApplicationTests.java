package com.future;

import com.future.util.SnowflakeIDAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(classes = FutureApplication.class)
public class FutureApplicationTests {
    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Test
    void ID(){
        SnowflakeIDAlgorithm snowflakeIDAlgorithm = new SnowflakeIDAlgorithm(15, 15);
        System.out.println(snowflakeIDAlgorithm.nextId());
    }
}
