package com.example.demo.service;


import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.entity.Usuarios;
import com.example.demo.repository.RepositoryUsuario;

@Service
public class UsuarioServicioImpl implements UsuarioService{

	@Autowired
	private RepositoryUsuario usuarioRepository;
	
	
	
	@Override
	public Usuarios saveUser(UsuarioRegistroDTO registroDTO) {
		Timestamp ts = Timestamp.from(Instant.now());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(registroDTO.getPassword());
	    registroDTO.setPassword(encodedPassword);
		Usuarios usuario = new Usuarios(registroDTO.getUsername(),registroDTO.getPassword(),null,registroDTO.getEmail(),registroDTO.getNombreCompleto(),registroDTO.getFecha_nac(),ts);
		return usuarioRepository.save(usuario);
	}
}
