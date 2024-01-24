package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

    /**
	 * method allowing user search based on a given username
	 * @param username
	 * @return
	 */
	Users findByUsername(String username);  


}
