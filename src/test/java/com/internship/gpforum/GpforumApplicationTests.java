package com.internship.gpforum;

import com.internship.gpforum.common.PasswordEncryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GpforumApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(PasswordEncryption.encryption_MD5("123123@qq.com"));
    }

}
