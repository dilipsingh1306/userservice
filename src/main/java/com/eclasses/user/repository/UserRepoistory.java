package com.eclasses.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eclasses.user.entity.UserRegisterEntity;

@Repository
public interface UserRepoistory  extends CrudRepository<UserRegisterEntity, String>{

	
}
