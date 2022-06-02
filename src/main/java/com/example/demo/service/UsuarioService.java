package com.example.demo.service;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.entity.Usuarios;

public interface UsuarioService {

	public Usuarios saveUser(UsuarioRegistroDTO resgistroDTO);
		
}
