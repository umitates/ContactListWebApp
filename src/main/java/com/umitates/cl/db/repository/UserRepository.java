package com.umitates.cl.db.repository;

import org.springframework.data.repository.CrudRepository;

import com.umitates.cl.db.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	public UserEntity findByUsername(String username);

}