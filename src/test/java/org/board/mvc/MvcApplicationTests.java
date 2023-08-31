package org.board.mvc;



import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class MvcApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void connectionTest() {
		try (Connection Connection = dataSource.getConnection()) {
			log.info("It's OK Database Connection");
		} catch (Exception e) {
			log.info("Connection Error");
		}
	}

}
