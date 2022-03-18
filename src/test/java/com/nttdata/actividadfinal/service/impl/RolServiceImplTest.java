package com.nttdata.actividadfinal.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.nttdata.actividadfinal.service.RolService;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class RolServiceImplTest {
	
	private Rol rol1, rol2;
	
	@Autowired
	RolRepoJPA rolRepo;
	
	@Autowired
	RolService service;
	
	@Autowired
	UserRepoJPA userRepo;
	

	@BeforeEach
	void setUp() throws Exception {
		userRepo.deleteAll();
		rolRepo.deleteAll();
		
		rol1 = new Rol();
		rol1.setId(1);
		rol1.setRol("ADMIN");
		rol1 = rolRepo.save(rol1);
		
		rol2 = new Rol();
		rol2.setId(2);
		rol2.setRol("CONSULTA");
		rol2 = rolRepo.save(rol2);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testListAll() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 roles in the BBDD");
		
		//WHEN:
		List<Rol> roles = service.listAll();
		
		//THEN:
		assertEquals(2, roles.size(), "2 roles in the BBDD");
		int found = 0;
		
		for (Rol rol: roles) {
			if (rol.equals(rol1) || rol.equals(rol2)) {
				found++;
			}
		}
		
		assertEquals(2, found, "Found rol1 and rol2");
	}

}
