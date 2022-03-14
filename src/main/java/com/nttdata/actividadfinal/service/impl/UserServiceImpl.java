package com.nttdata.actividadfinal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nttdata.actividadfinal.repository.UserRepoJPA;
import com.nttdata.actividadfinal.repository.entity.User;
import com.nttdata.actividadfinal.service.UserService;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepoJPA userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username);
	}

	@Override
	public List<User> listAll() {
		return userDAO.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findById(username).get();
	}

	@Override
	public List<User> findByRol(Integer rol) {
		return userDAO.findByRolId(rol);
	}
}
