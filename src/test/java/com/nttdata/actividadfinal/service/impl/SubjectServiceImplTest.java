package com.nttdata.actividadfinal.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.nttdata.actividadfinal.repository.SubjectRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Subject;
import com.nttdata.actividadfinal.service.SubjectService;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class SubjectServiceImplTest {
	
	private Subject sub1, sub2;
	
	@Autowired
	SubjectRepoJPA repo;
	
	@Autowired
	SubjectService service;
	

	@BeforeEach
	void setUp() throws Exception {
		repo.deleteAll();
		
		sub1 = new Subject();
		sub1.setId(1);
		sub1.setName("Álgebra");
		sub1.setDescription("Álgebra II");
		sub1.setCourse(2);
		sub1 = repo.save(sub1);
		
		sub2 = new Subject();
		sub2.setId(2);
		sub2.setName("Cálculo");
		sub2.setDescription("Cálculo II");
		sub2.setCourse(2);
		sub2 = repo.save(sub2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListAll() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		List<Subject> subjects = service.listAll();
		
		//THEN:
		assertEquals(2, subjects.size(), "2 subjects in the BBDD");
		int found = 0;
		
		for (Subject sub: subjects) {
			if (sub.equals(sub1) || sub.equals(sub2)) {
				found++;
			}
		}
		
		assertEquals(2, found, "Found sub1 and sub2");
	}

	@Test
	void testGetById() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub = service.getById( sub1.getId() );
			
		//THEN:
		assertAll(
				() -> assertEquals(sub1.getId(), sub.getId(), "Same subject"),
				() -> assertNotNull(sub, "Valid subject")
		);
	}

	@Test
	void testDeleteAll() throws Exception {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		service.deleteAll();
		
		//THEN:
		assertEquals(0, service.listAll().size(), "0 subjects in the BBDD");
		
		this.setUp();
	}

	@Test
	void testDelete() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		service.delete(sub1.getId());
		
		//THEN:
		assertEquals(1, service.listAll().size(), "1 subject in the BBDD");
		
		sub1 = repo.save(sub1);
	}

	@Test
	void testCreate() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub3 = new Subject();
		sub3.setName("Física");
		sub3.setDescription("Física II");
		sub3.setCourse(2);
		sub3 = service.create(sub3);
		
		//THEN:
		assertEquals(3, service.listAll().size(), "3 subject in the BBDD");
		
		service.delete(sub3.getId());
	}

	@Test
	void testEdit() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		assertEquals("Cálculo", service.getById(sub2.getId()).getName(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub = service.getById(sub2.getId());
		sub.setName("Otro");
		service.edit(sub);
		
		//THEN:
		assertAll(
				() -> assertEquals(2, service.listAll().size(), "2 subjects in the BBDD"),
				() -> assertNotEquals(sub2.getName(), service.getById(sub2.getId()).getName(), "Subjects not equal")
		);
		
		service.edit(sub2);
	}

}
