package com.umitates.cl.db.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.repository.CrudRepository;

import com.umitates.cl.db.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	public UserEntity findByUsername(String username);

	@CacheEvict(value = "user", key = "#id")
    void delete(String id);
}