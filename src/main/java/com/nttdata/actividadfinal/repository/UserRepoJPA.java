package com.nttdata.actividadfinal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nttdata.actividadfinal.repository.entity.User;


public interface UserRepoJPA extends JpaRepository<User, String> {
	
	List<User> findByRolId(Integer rol);
	
}
