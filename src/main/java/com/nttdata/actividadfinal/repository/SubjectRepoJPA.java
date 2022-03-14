package com.nttdata.actividadfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nttdata.actividadfinal.repository.entity.Subject;


public interface SubjectRepoJPA extends JpaRepository<Subject, Integer> {
	
}
