package br.com.joaof.todolist.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joaof.todolist.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	User findByUserName(String nome);
}
