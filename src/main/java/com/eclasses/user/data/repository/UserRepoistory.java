package com.eclasses.user.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eclasses.user.data.entity.UserRegisterEntity;

@Repository
public interface UserRepoistory  extends CrudRepository<UserRegisterEntity, String>{

	
}
