package com.nttdata.actividadfinal.restcontroller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nttdata.actividadfinal.components.SubjectValidator;
import com.nttdata.actividadfinal.components.ValidationException;
import com.nttdata.actividadfinal.repository.entity.Subject;
import com.nttdata.actividadfinal.service.SubjectService;


@RestController
@RequestMapping ("/api/subjects")
public class SubjectRestController {
	
	private static String MESSAGE = "Message";
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	SubjectValidator subjectValidator;
	
	
	@GetMapping
	@Cacheable(value="subjects")
	public ResponseEntity<List<Subject>> getAllSubjects() {
		try {
			return new ResponseEntity<> (subjectService.listAll(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<> (new ArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping (value="/{id}")
	public ResponseEntity<Subject> getSubject(@PathVariable("id") Integer id) {
		try {
			Subject subject = subjectService.getById(id);
			if (subject != null) {
				return new ResponseEntity<> (subject, HttpStatus.OK);
			} else {
				return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping
	@CacheEvict (value="subjects", allEntries = true)
	public ResponseEntity<List<Subject>> deleteAllSubjects() {
		try {
			subjectService.deleteAll();
			return new ResponseEntity<> (subjectService.listAll(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<> (new ArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}")
	@CacheEvict (value="subjects", allEntries = true)
	public ResponseEntity<List<Subject>> deleteSubject(@PathVariable("id") Integer id) {
		try {
			subjectService.delete(id);
			return new ResponseEntity<> (subjectService.listAll(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<> (new ArrayList(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	@CacheEvict (value="subjects", allEntries = true)
	public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
		try {
			HttpHeaders headers = new HttpHeaders();
			
			try {
				subjectValidator.validate(subject, true);
			} catch (ValidationException e) {
				headers.set(MESSAGE, e.getMessage());
				return new ResponseEntity<> (null, headers, HttpStatus.NOT_ACCEPTABLE);
			}
			
			Subject created = subjectService.create(subject);
			URI newPath = new URI("/api/subjects/" + created.getId());
			headers.setLocation(newPath);
			headers.set(MESSAGE, "Asignatura insertada correctamente con id: " + created.getId());
			
			return new ResponseEntity<> (created, headers, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	@CacheEvict (value="subjects", allEntries = true)
	public ResponseEntity<Subject> editSubject(@RequestBody Subject subject) {
		try {
			HttpHeaders headers = new HttpHeaders();
			
			try {
				subjectValidator.validate(subject, false);
			} catch (ValidationException e) {
				headers.set(MESSAGE, e.getMessage());
				return new ResponseEntity<> (null, headers, HttpStatus.NOT_ACCEPTABLE);
			}
			
			Subject edited = subjectService.edit(subject);
			URI newPath = new URI("/api/subjects/" + edited.getId());
			headers.setLocation(newPath);
			headers.set(MESSAGE, "Asignatura editada correctamente con id: " + edited.getId());
			
			return new ResponseEntity<> (edited, headers, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
