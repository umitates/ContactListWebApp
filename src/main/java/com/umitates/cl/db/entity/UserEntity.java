package com.umitates.cl.db.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserEntity {

	@Id
	private String id;
	private String username;
	private String password;
	private String role;
	private List<ContactEntity> contacts;

	public UserEntity() {
		super();
	}

	public UserEntity(String id, String username, String password, String role, List<ContactEntity> contacts) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.contacts = contacts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ContactEntity> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactEntity> contacts) {
		this.contacts = contacts;
	}

}
