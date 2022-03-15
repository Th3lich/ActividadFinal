package com.nttdata.actividadfinal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.actividadfinal.repository.SubjectRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Subject;
import com.nttdata.actividadfinal.service.SubjectService;


@Service
public class SubjectServiceImpl implements SubjectService  {
	
	@Autowired
	SubjectRepoJPA subjectRepo;
	
	@Override
	public List<Subject> listAll() {
		return subjectRepo.findAll();
	}

	@Override
	public Subject getById(Integer id) {
		return subjectRepo.findById(id).orElse(null);
	}

	@Override
	public void deleteAll() {
		subjectRepo.deleteAll();
	}

	@Override
	public void delete(Integer id) {
		subjectRepo.deleteById(id);
	}

	@Override
	public Subject create(Subject subject) {
		return subjectRepo.save(subject);
	}

	@Override
	public Subject edit(Subject subject) {
		return subjectRepo.save(subject);
	}
}
