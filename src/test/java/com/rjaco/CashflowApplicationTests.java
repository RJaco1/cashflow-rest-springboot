package com.rjaco;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rjaco.dao.IUserAccountDAO;
import com.rjaco.model.UserAccount;

@SpringBootTest
class CashflowApplicationTests {

	@Autowired
	private IUserAccountDAO userDao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	/*
	 * @Test
	 * void contextLoads() {
	 * }
	 */

	/* @Test
	public void createUser() {
		UserAccount user = new UserAccount();
		//user.setUserId(2);
		user.setUsername("user123");
		user.setEmail("example1@email.com");
		user.setPassword(bcrypt.encode("123"));

		UserAccount res = userDao.save(user);

		assertTrue(res.getPassword().equalsIgnoreCase(user.getPassword()));
	} */

}
