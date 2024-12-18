package com.example.demo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Usuarios;

public class CustomUserDetails implements UserDetails{

	 private Usuarios user;
	 
	 public CustomUserDetails(Usuarios user) {
	        this.user = user;
	    }
	 
	 	@Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return null;
	    }
	 
	    @Override
	    public String getPassword() {
	        return user.getPassword();
	    }
	 
	    @Override
	    public String getUsername() {
	        return user.getUsername();
	    }
	    
	    public String getFile() {
	        return user.getFile();
	    }
	    
	    public String getNombreCompleto() {
	        return user.getNombreCompleto();
	    }
	    
	    public String getEmail() {
	        return user.getEmail();
	    }
	    
	    public long getIdUsuario() {
	    	return user.getId();
	    }
	    
	    public String getFechNac() {
	    	return user.getFechaNac();
	    }
	    
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	 
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	 
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 
	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
	     
	
}
