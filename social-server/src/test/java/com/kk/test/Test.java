package com.kk.test;

import com.kk.util.JwtTokenUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {
    @org.junit.Test
    public void random() {
        String token = JwtTokenUtil.createToken("KK", "kk");
        System.out.println(token);
        boolean expiration = JwtTokenUtil.isExpiration(token);
        System.out.println(expiration);
    }
}
