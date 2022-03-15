package com.nttdata.actividadfinal.service;

import java.util.List;
import com.nttdata.actividadfinal.repository.entity.Subject;


public interface SubjectService {
	List<Subject> listAll();
	Subject getById(Integer id);
	void deleteAll();
	void delete(Integer id);
	Subject create(Subject subject);
	Subject edit(Subject subject);
}
