package com.nttdata.actividadfinal.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.nttdata.actividadfinal.repository.RolRepoJPA;
import com.nttdata.actividadfinal.repository.UserRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Rol;
import com.nttdata.actividadfinal.repository.entity.User;
import com.nttdata.actividadfinal.service.UserService;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class UserServiceImplTest {
	
	private User user1, user2;
	
	@Autowired
	RolRepoJPA rolRepo;
	
	@Autowired
	UserRepoJPA userRepo;
	
	@Autowired
	UserService service;
	

	@BeforeEach
	void setUp() throws Exception {
		userRepo.deleteAll();
		rolRepo.deleteAll();
		
		Rol rol1 = new Rol();
		rol1.setId(1);
		rol1.setRol("ADMIN");
		rol1 = rolRepo.save(rol1);
		
		Rol rol2 = new Rol();
		rol2.setId(2);
		rol2.setRol("CONSULTA");
		rol2 = rolRepo.save(rol2);
		
		user1 = new User();
		user1.setUsername("admin");
		user1.setFirstname("User 1");
		user1.setSurnames("surnames");
		user1.setPassword("1111");
		user1.setRol(rol1);
		user1 = userRepo.save(user1);
		
		user2 = new User();
		user2.setUsername("consulta");
		user2.setFirstname("User 2");
		user2.setSurnames("surnames");
		user2.setPassword("1111");
		user2.setRol(rol2);
		user2 = userRepo.save(user2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	

	@Test
	void testListAll() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 users in the BBDD");
		
		//WHEN:
		List<User> users = service.listAll();
		
		//THEN:
		assertEquals(2, users.size(), "2 users in the BBDD");
		int found = 0;
		
		for (User user: users) {
			if (user.equals(user1) || user.equals(user2)) {
				found++;
			}
		}
		
		assertEquals(2, found, "Found user1 and user2");
	}

	@Test
	void testFindByUsername() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 users in the BBDD");
		
		//WHEN:
		User user = service.findByUsername("admin");
		
		//THEN:
		assertAll(
				() -> assertEquals(user1.getUsername(), user.getUsername(), "Same subject"),
				() -> assertNotNull(user, "Valid subject")
		);
	}

	@Test
	void testFindByRol() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 users in the BBDD");
		
		//WHEN:
		List<User> users = service.findByRol(1);
		
		//THEN:
		assertEquals(1, users.size(), "1 users with rol 1");
	}

}
