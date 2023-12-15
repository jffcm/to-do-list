package br.com.joaof.todolist.models;

import java.time.LocalDateTime;
import java.util.UUID; 

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name ="tb_users")
public class User {
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	@Column(unique=true)
	private String userName;
	private String name;
	private String password;
	
	@CreationTimestamp
	private LocalDateTime createdAt;

	public User() {}
	
	public User(String userName, String name, String password) {
		this.userName = userName;
		this.name = name;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", name=" + name + ", password=" + password + "]";
	}
}
