package com.nttdata.actividadfinal.service;

import java.util.List;
import com.nttdata.actividadfinal.repository.entity.User;


public interface UserService {
	List<User> listAll();
	User findByUsername(String username);
	List<User> findByRol(Integer rol);
}
