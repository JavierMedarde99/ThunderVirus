package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdate {
	
	private long idusuario;
	
	private String nombreCompleto;
	
	private String username;
	
	private String password;
	
	private String password2;
	
	private String email;
	
	private String fechaNac;
	
}
