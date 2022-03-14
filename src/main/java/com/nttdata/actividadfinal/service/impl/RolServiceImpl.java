package com.nttdata.actividadfinal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nttdata.actividadfinal.repository.RolRepoJPA;
import com.nttdata.actividadfinal.repository.entity.Rol;
import com.nttdata.actividadfinal.service.RolService;


@Service
public class RolServiceImpl implements RolService {
	
	@Autowired
	RolRepoJPA rolRepo;
	

	@Override
	public List<Rol> listAll() {
		return rolRepo.findAll();
	}

}
