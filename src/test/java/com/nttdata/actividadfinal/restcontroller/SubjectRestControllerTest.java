package com.nttdata.actividadfinal.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import com.nttdata.actividadfinal.repository.SubjectRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Subject;
import com.nttdata.actividadfinal.service.SubjectService;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class SubjectRestControllerTest {
	
	private Subject sub1, sub2;

	@Autowired
	SubjectRepoJPA repo;
	
	@Autowired
	SubjectService service;
	
	@Autowired
	SubjectRestController restcontroller;
	
	@Mock
	SubjectService serviceMock;
	
	@InjectMocks
	SubjectRestController restcontrollerMock;
	

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
		repo.deleteAll();
	}

	@Test
	void testGetAllSubjects() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
	
		//WHEN:
		ResponseEntity<List<Subject>> re = restcontroller.getAllSubjects();
		
		//THEN:
		assertEquals(service.listAll().size(), re.getBody().size(), "2 subjects listed" );
		assertThat(re.getStatusCodeValue()).isEqualTo(200);
		assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testGetSubject() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
	
		//WHEN:
		ResponseEntity<Subject> re = restcontroller.getSubject(sub1.getId());
		
		//THEN:
		assertEquals(sub1, re.getBody(), "Subject sub1" );
		assertThat(re.getStatusCodeValue()).isEqualTo(200);
		assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testDeleteAllSubjects() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		restcontroller.deleteAllSubjects();
			
		//THEN:
		assertEquals(0, service.listAll().size(), "0 subjects left");
		
		sub1 = repo.save(sub1);
		sub2 = repo.save(sub2);
	}

	@Test
	void testDeleteSubject() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		restcontroller.deleteSubject(sub2.getId());
			
		//THEN:
		assertEquals(1, service.listAll().size(), "Only one subject left");
		
		sub2 = repo.save(sub2);
	}

	@Test
	void testCreateSubject() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub3 = new Subject();
		sub3.setName("Asignatura 3");
		sub3.setDescription("Descripción");
		sub3.setCourse(3);
		sub3 = restcontroller.createSubject(sub3).getBody();
			
		//THEN:
		assertEquals(3, service.listAll().size(), "3 subjects in the BBDD");
		
		service.delete(sub3.getId());
	}

	@Test
	void testEditSubject() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub = sub2;
		sub.setName("Asignatura 2");
		restcontroller.editSubject(sub);
		
		//THEN:
		assertEquals(sub.getName(), service.getById(sub2.getId()).getName(), "Same name");
	}
	
	@Test
	void testCreateSubjectIdNotnNull() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub3 = new Subject();
		sub3.setId(88);
		sub3.setName("Asignatura 3");
		sub3.setDescription("Descripción");
		sub3.setCourse(3);
		ResponseEntity<Subject> re = restcontroller.createSubject(sub3);
			
		//THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 id is not null");
	}
	
	@Test
	void testCreateSubjectNameNull() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub3 = new Subject();
		sub3.setDescription("Descripción");
		sub3.setCourse(3);
		ResponseEntity<Subject> re = restcontroller.createSubject(sub3);
			
		//THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 name is null");
	}
	
	@Test
	void testCreateSubjectDescriptionNull() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub3 = new Subject();
		sub3.setName("Asignatura 3");
		sub3.setCourse(3);
		ResponseEntity<Subject> re = restcontroller.createSubject(sub3);
			
		//THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 description is null");
	}
	
	@Test
	void testCreateSubjectCurseNull() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub3 = new Subject();
		sub3.setName("Asignatura 3");
		sub3.setDescription("Descripción");
		ResponseEntity<Subject> re = restcontroller.createSubject(sub3);
			
		//THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 curse is null");
	}
	
	@Test
	void testDeleteSubjectNotExits() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		ResponseEntity<List<Subject>> re = restcontroller.deleteSubject(-1);
			
		//THEN:
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Error 406 subject does not exists");
	}
	
	@Test
	void testEditSubjectNotExits() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub = new Subject();
		sub.setId(-1);
		sub.setName("Asignatura inexistente");
		sub.setDescription("Descripción");
		sub.setCourse(3);
		ResponseEntity<Subject> re = restcontroller.editSubject(sub);
			
		//THEN:
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, re.getStatusCode(), "Error 406 subject does not exists");
	}
	
	@Test
	void testEditSubjectNameEmpty() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		Subject sub = sub2;
		sub.setName("");
		ResponseEntity<Subject> re = restcontroller.editSubject(sub);
			
		//THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE, re.getStatusCode(), "Error 406 subject name empty");
	}
	
	@Test
	void testGetSubjectNotExist() {
		//GIVEN:
		assertEquals(2, service.listAll().size(), "2 subjects in the BBDD");
		
		//WHEN:
		ResponseEntity<Subject> re = restcontroller.getSubject(-1);
			
		//THEN:
		assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode(), "Error 406 subject does not exists");
	}
}
