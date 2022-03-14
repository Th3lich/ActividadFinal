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
	
}
