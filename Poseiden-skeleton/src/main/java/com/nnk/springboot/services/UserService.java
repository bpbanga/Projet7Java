package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Users;
import com.nnk.springboot.repositories.UserRepository;
@Service
public class UserService implements UserDetailsService {


     @Autowired
    private  UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger("UserService");


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        
		return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));  
    }

    /**
     * method allowing to give authorities for different users
	 * @param role type of autority for user
	 * @return
	 */
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities ;
	}

    public boolean verifyUserPassword(String userPassword){
        boolean verif = false;
        final int MIN=8;
        final int MIN_Uppercase=1;
        final int MIN_Lowercase=2;
        final int NUM_Digits=1;
        final int Special=1;
        int lowercaseCounter=0;
        int uppercaseCounter=0;
        // Count digits in a password
        int digitCounter=0;
        // count special case letters in a password
        int specialCounter=0; 
        
        for (int i=0; i < userPassword.length(); i++ ) {
                    char c = userPassword.charAt(i);
                    if(Character.isUpperCase(c)){ 
                        uppercaseCounter =  uppercaseCounter + 1;
                    }
                    else if(Character.isLowerCase(c)){ 
                        lowercaseCounter =  lowercaseCounter + 1;
                    }
                    else if(Character.isDigit(c)){
                        digitCounter =   digitCounter + 1;
                    } 
                             
                    else if(c == '$' || c == '#' || c == '?' || c == '!' || c == '_' || c == '=' || c == '%'
                        || c == '`' || c == '~' || c == '{' || c == '}' || c == '@' || c == '[' || c == ']' ){
                        specialCounter = specialCounter + 1;
                 }                    
        }
        if (userPassword.length() >= MIN && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase
            && specialCounter >= Special && digitCounter >= NUM_Digits) {
                verif = true;
            }
        else{
            verif = false;
        }
    return verif;

    }


    public List<Users> getUsers() {

	return (List<Users>)userRepository.findAll();
    }



    public void postUser( Users userToAdd){

        userRepository.save(userToAdd);
    }


    public void putUser(int userToUpdateId, String usernameUp , String passwordUp, String fullnameUp , String roleUp)  {
        Optional <Users> userUpdate = userRepository.findById(userToUpdateId);
        if ( userUpdate.isPresent()){
            userUpdate.get().setUsername(usernameUp);
            userUpdate.get().setPassword(passwordUp);
            userUpdate.get().setFullname(fullnameUp);
            userUpdate.get().setRole(roleUp);
            userRepository.save(userUpdate.get());
        }
        
        
    }

    public void deleteUser(int userToDeleteId){
        Optional <Users> userToDelete = userRepository.findById(userToDeleteId);
        if ( userToDelete.isPresent()){

            userRepository.delete(userToDelete.get());
        }



    }



    
}
