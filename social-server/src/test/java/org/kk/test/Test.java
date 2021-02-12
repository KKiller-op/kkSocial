package org.kk.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {
    @org.junit.Test
    public void random(){
        var map = new HashMap<String, String>();
        map.put("hello","world");
        map.forEach((key, value) -> {
            System.out.println(key + "=" + value);
        });
    }
}
