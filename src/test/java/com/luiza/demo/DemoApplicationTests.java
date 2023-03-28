package com.luiza.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootTest
@EnableMongoRepositories
class DemoApplicationTests {


	@SuppressWarnings("squid:S2699")
	@Test
	void contextLoads() {
	}

}
