package com.nttdata.actividadfinal.components;

import org.springframework.stereotype.Component;
import com.nttdata.actividadfinal.repository.entity.Subject;


@Component
public class SubjectValidator implements CustomValidator {

	@Override
	public void validate(Object target, boolean creating) throws ValidationException {
		Subject subject = (Subject) target;
		
		
		if (creating && subject.getId() != null) {
			throw new ValidationException("Para dar de alta una nueva asignatura, el ID debe llegar vacío");
		}
		
		if (subject.getName() == null || subject.getName().isBlank()) {
			throw new ValidationException("El nombre de la asignatura no puede estar vacío {name}");
		}
		
		if (subject.getDescription() == null || subject.getDescription().isBlank()) {
			throw new ValidationException("La descripción de la asignatura no puede estar vacía {description}");
		}
		
		if (subject.getCourse() == null) {
			throw new ValidationException("El curso de la asignatura no puede estar vacío {course}");
		}
	}
}
