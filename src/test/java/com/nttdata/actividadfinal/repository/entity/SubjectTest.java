package com.nttdata.actividadfinal.repository.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class SubjectTest {

	@Test
	void test() {
		Subject s1 = new Subject();
		
		s1.setId(1);
		assertEquals(1, s1.getId(), "Same id");
		
		String name = "Test name";
		s1.setName(name);
		assertEquals(name, s1.getName(), "Same error");
		
		String description = "Test description";
		s1.setDescription(description);
		assertEquals(description, s1.getDescription(), "Same description");
		
		Integer course = 1;
		s1.setCourse(course);
		assertEquals(course, s1.getCourse(), "Same course");
		
		Subject s2 = new Subject();
		s2.setId(1);
		s2.setName(name);
		s2.setDescription(description);
		s2.setCourse(course);
		assertEquals(s1, s2, "Same subject");
		assertEquals(s1.hashCode(), s2.hashCode(), "Same hash code");
		
		assertNotEquals(s1, name);
		
		s1.setId(null);
		assertNotEquals(s1, s2, "s1 to null and s2 not");
	}

}
