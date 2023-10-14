package br.com.joaof.todolist.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaof.todolist.models.Task;
import br.com.joaof.todolist.repositories.TaskRepository;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("/")
	public ResponseEntity<Object> create(@RequestBody Task task, HttpServletRequest request) {
		
		try {
			var idUser = request.getAttribute("idUser");
			task.setIdUser((UUID) idUser);
			
			if (task.getStartAt().isAfter(task.getEndAt()) || task.getEndAt().isBefore(task.getStartAt())) {
				return ResponseEntity.badRequest().build();
			}
			
			var taskCreated = taskRepository.save(task);
			return ResponseEntity.ok(taskCreated);
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/")
	public List<Task> list(HttpServletRequest request) {
		var idUser = request.getAttribute("idUser");
		var tasks = taskRepository.findByIdUser((UUID) idUser);
		return tasks;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Task task, HttpServletRequest request) {
		Optional<Task> optional = taskRepository.findById(id);
		var idUser = request.getAttribute("idUser");
		
		
		if (optional.isPresent()) {
			var existingTask = optional.get();
			
			if (existingTask.getIdUser().equals(idUser)) {
				existingTask.setAttribute(task);
				taskRepository.save(existingTask);
				
				return ResponseEntity.ok(existingTask);
				
			} else {
				return ResponseEntity.badRequest().build();
			}
						
		} else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
}
