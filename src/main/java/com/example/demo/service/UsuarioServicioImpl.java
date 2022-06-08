package com.example.demo.service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.dto.UsuarioUpdate;
import com.example.demo.entity.Usuarios;
import com.example.demo.repository.RepositoryUsuario;

@Service
public class UsuarioServicioImpl implements UsuarioService{
	
	final Double tarifa=5.0;
	
	@Autowired
	private RepositoryUsuario usuarioRepository;
	
	
	
	@Override
	public Usuarios saveUser(UsuarioRegistroDTO registroDTO) {
		Timestamp ts = Timestamp.from(Instant.now());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(registroDTO.getPassword());
	    registroDTO.setPassword(encodedPassword);
		Usuarios usuario = new Usuarios(registroDTO.getUsername(),registroDTO.getPassword(),registroDTO.getUsername()+".png",registroDTO.getEmail(),registroDTO.getNombreCompleto(),registroDTO.getFecha_nac(),ts);
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public Usuarios updateFile(long id,String foto) {
		Optional<Usuarios> check = usuarioRepository.checkId(id);
		
		
		if(check.isPresent()) {
			Usuarios user =check.get();
			user.setFile(foto);
			usuarioRepository.save(user);
		}
		
		return null;
	}
	
	@Override
	public Usuarios updateUser(UsuarioUpdate usuarioUpdate) {
		 if(usuarioUpdate.getPassword() !=null) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 String encodedPassword = passwordEncoder.encode(usuarioUpdate.getPassword2());
		 usuarioUpdate.setPassword2(encodedPassword);
		 }
		 Usuarios changeUser =  usuarioRepository.checkId(usuarioUpdate.getIdusuario()).get();
		 
		 changeUser.setNombreCompleto(usuarioUpdate.getNombreCompleto());
		 changeUser.setEmail(usuarioUpdate.getEmail());
		 changeUser.setUsername(usuarioUpdate.getUsername());
		 if(usuarioUpdate.getPassword() !=null) {
			 changeUser.setPassword(usuarioUpdate.getPassword2());
		 }
		 changeUser.setFecha_nac(usuarioUpdate.getFecha_nac());
		 
		 return usuarioRepository.save(changeUser);
		 
	}
	
	@Override
	public Usuarios subUser(Integer tarjetaCredito,long idUsuario) {
		 Usuarios subUser =  usuarioRepository.checkId(idUsuario).get();
		 Timestamp ts = Timestamp.from(Instant.now());
		 subUser.setDinero(tarifa);
		 subUser.setSub(true);
		 subUser.setTarifa(tarifa);
		 subUser.setTarjetaCredito(tarjetaCredito);
		 subUser.setFecha_ini_sub(ts);
		 subUser.setFecha_fin_sub(null);
		 
		return usuarioRepository.save(subUser);
	}
	
	@Override
	public Usuarios unsubUser(long idUsuario) {
		Usuarios subUser =  usuarioRepository.checkId(idUsuario).get();
		 Timestamp ts = Timestamp.from(Instant.now());
		 subUser.setDinero(0);
		 subUser.setSub(false);
		 subUser.setTarifa(0);
		 subUser.setTarjetaCredito(0);
		 subUser.setFecha_fin_sub(ts);
		 
		 return usuarioRepository.save(subUser);
	}
}
