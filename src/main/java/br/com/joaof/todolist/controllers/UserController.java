package br.com.joaof.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.joaof.todolist.models.User;
import br.com.joaof.todolist.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody User user) {
		
		try {
			String passwordHashred = BCrypt.withDefaults()
			.hashToString(12, user.getPassword().toCharArray());
			
			user.setPassword(passwordHashred);
			
			var userCreated = userRepository.save(user);
			return ResponseEntity.ok(userCreated);
			
		} catch (DataIntegrityViolationException e) {
			return (
			ResponseEntity.badRequest()
			.body("Nome de usuário já usado!")
			);
		}
	}
}
