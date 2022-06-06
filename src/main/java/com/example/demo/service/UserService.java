package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.Usuarios;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.repository.RepositoryUsuario;

public class UserService implements UserDetailsService{

	@Autowired
	private RepositoryUsuario userReposy;
	
	
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<Usuarios> user = userReposy.findByUsername(username);

	        if (user.isEmpty()) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        return new CustomUserDetails(user.get());
	    }
	
}
