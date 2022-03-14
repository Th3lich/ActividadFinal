package com.nttdata.actividadfinal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nttdata.actividadfinal.repository.entity.User;
import com.nttdata.actividadfinal.service.RolService;
import com.nttdata.actividadfinal.service.SubjectService;
import com.nttdata.actividadfinal.service.UserService;


@Controller
public class WebController {
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RolService rolService;
	
	
	@GetMapping("/")
	public String index(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", user);
		return "index";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list_subjects")
	public String listSubjects(Model model) {
		model.addAttribute("subjects", subjectService.listAll());
		return "subjects";
	}
	
	@GetMapping("/list_users")
	public String listUsers(@RequestParam int rol, Model model) {
		List<User> listUsers = new ArrayList<>();
		
		if (rol == 0) {
			listUsers = userService.listAll();
		} else {
			listUsers = userService.findByRol(rol);
		}
		
		model.addAttribute("users", listUsers);
		model.addAttribute("roles", rolService.listAll());
		return "users";
	}
	
	@GetMapping("/error")
	public String errorPage() {
		return "error";
	}
}
