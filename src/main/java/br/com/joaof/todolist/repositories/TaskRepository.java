package br.com.joaof.todolist.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joaof.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID>{
	List<Task> findByIdUser(UUID id);
}
