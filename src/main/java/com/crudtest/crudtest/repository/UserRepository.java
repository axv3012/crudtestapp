package com.crudtest.crudtest.repository;

import com.crudtest.crudtest.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

    List<UserEntity> findByLastName(String lastName);
}
