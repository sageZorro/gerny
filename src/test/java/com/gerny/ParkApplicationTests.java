package com.gerny;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkApplicationTests {

	@Test
	public void contextLoads() {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		String password = encode.encode("123456");
		System.out.println(password);
	}

}
