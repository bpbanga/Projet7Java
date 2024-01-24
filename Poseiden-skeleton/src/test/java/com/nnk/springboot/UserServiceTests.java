package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Users;
import com.nnk.springboot.services.UserService;



@SpringBootTest
public class UserServiceTests {

    @Autowired
	private UserService userService;
	private static int idUser;

	 private static Users userToAdd = new Users();

	@BeforeAll
    public  static void setup() {
		userToAdd.setId(102);
        userToAdd.setUsername("usernameUp");
        userToAdd.setPassword("passwordUp");
        userToAdd.setFullname("fullnameUp");
        userToAdd.setRole("roleUp");
        idUser = 102;
    }

	@AfterEach
    public void deleteAddedUsers() {
	userService.deleteUser(idUser);
    }
	

	@Test
	public void shouldRetrieveNonEMptyRUsers(){
		Assertions.assertNotNull(userService.getUsers());
	}
	@Test
	public void shouldAddSuccessfullyUsers(){
		userService.postUser(userToAdd);
		List<Users> listRatings = userService.getUsers();
		Assertions.assertEquals(listRatings.get(listRatings.size() - 1).getFullname(),
												 userToAdd.getFullname());
	}

	@Test
	public void shouldEditSuccefullyUsers(){
		userService.postUser(userToAdd);
		List<Users> listUsers = userService.getUsers();
		int initSize = listUsers.size();
		Users userToEdit = new Users();
		userToEdit.setId(listUsers.get(initSize-1).getId());	
     	userToEdit.setUsername("usernameUps");
        userToEdit.setPassword("passwordUps");
        userToEdit.setFullname("fullnameUps");
        userToEdit.setRole("roleUps");

		userService.putUser(userToEdit.getId(),userToEdit.getUsername(),userToEdit.getPassword(),
								  userToEdit.getFullname(), userToEdit.getRole());
		listUsers = userService.getUsers();
		initSize = listUsers.size();
		Assertions.assertEquals(userToEdit.getUsername(), listUsers.get(initSize-1).getUsername());
		Assertions.assertEquals(userToEdit.getPassword(), listUsers.get(initSize-1).getPassword());
		Assertions.assertEquals(userToEdit.getFullname(), listUsers.get(initSize-1).getFullname());
		Assertions.assertEquals(userToEdit.getRole(), listUsers.get(initSize -1).getRole());
	}

	@Test
	public void shouldNotEditSuccefullyUsers(){
		userService.postUser(userToAdd);
		List<Users> listUsers = userService.getUsers();
		int initSize = listUsers.size();
		Users userToEdit = new Users();
		userToEdit.setId(listUsers.get(initSize-1).getId()+1);	
     	userToEdit.setUsername("usernameUps");
        userToEdit.setPassword("passwordUps");
        userToEdit.setFullname("fullnameUps");
        userToEdit.setRole("roleUps");

		userService.putUser(userToEdit.getId(),userToEdit.getUsername(),userToEdit.getPassword(),
								  userToEdit.getFullname(), userToEdit.getRole());
		listUsers = userService.getUsers();
		initSize = listUsers.size();
		Assertions.assertNotEquals(userToEdit.getUsername(), listUsers.get(initSize-1).getUsername());
		Assertions.assertNotEquals(userToEdit.getPassword(), listUsers.get(initSize-1).getPassword());
		Assertions.assertNotEquals(userToEdit.getFullname(), listUsers.get(initSize-1).getFullname());
		Assertions.assertNotEquals(userToEdit.getRole(), listUsers.get(initSize -1).getRole());
	}

	@Test
    public void shouldDeleteSuccessfullyUsers() {
		List<Users> listUsers = userService.getUsers();
		int initSize = listUsers.size();

		userService.postUser(userToAdd);
		listUsers = userService.getUsers();
		assertEquals(initSize + 1, listUsers.size());

		userService.deleteUser(listUsers.get(initSize).getId());
		listUsers = userService.getUsers();	
		assertEquals(initSize, listUsers.size());

		
    }

}
