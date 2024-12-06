package com.example.demo.service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.dto.UsuarioUpdate;
import com.example.demo.entity.Comentarios;
import com.example.demo.entity.Participacion;
import com.example.demo.entity.Usuarios;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.repository.Repositorycomentarios;
import com.example.demo.utils.Constants;

import lombok.RequiredArgsConstructor;

import com.example.demo.repository.RepositoryEvento;
import com.example.demo.repository.RepositoryJugadores;
import com.example.demo.repository.RepositoryParticipacion;
import com.example.demo.repository.RepositoryUsuario;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioService{
	
	
	
	private final RepositoryUsuario usuarioRepository;
	
	private final RepositoryParticipacion repositoryparticipacion;
	
	private final Repositorycomentarios repositorycomentarios;

	private final RepositoryEvento repositoryEvento;

	private final RepositoryJugadores reposiJugadores;
	
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
			return usuarioRepository.save(user);
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
		 changeUser.setFechaNac(usuarioUpdate.getFechaNac());
		 
		 return usuarioRepository.save(changeUser);
		 
	}
	
	@Override
	public Usuarios subUser(String tarjetaCredito,long idUsuario) {
		 Usuarios subUser =  usuarioRepository.checkId(idUsuario).get();
		 Timestamp ts = Timestamp.from(Instant.now());
		 if(subUser.getDinero()!=0) {
			 subUser.setDinero(subUser.getDinero()+Constants.TARIFA);
		 }else {
			 subUser.setDinero(Constants.TARIFA);
		 }
		 
		 subUser.setSub(true);
		 subUser.setTarifa(Constants.TARIFA);
		 subUser.setTarjetaCredito(tarjetaCredito);
		 subUser.setFechaIniSub(ts);
		 subUser.setFechaFinSub(null);
		 
		return usuarioRepository.save(subUser);
	}
	
	@Override
	public Usuarios unsubUser(long idUsuario) {
		Usuarios subUser =  usuarioRepository.checkId(idUsuario).get();
		 Timestamp ts = Timestamp.from(Instant.now());
		 subUser.setDinero(0);
		 subUser.setSub(false);
		 subUser.setTarifa(0);
		 subUser.setTarjetaCredito("");
		 subUser.setFechaFinSub(ts);
		 
		 return usuarioRepository.save(subUser);
	}
	
	@Override
	public Participacion unirseEvento(long idUsuario,Long idEvento) {
		Timestamp ts = Timestamp.from(Instant.now());
		Participacion part = new Participacion();
		
		part.setEvento(repositoryEvento.findById(idEvento).get());
		part.setUsuarios(usuarioRepository.findById(idUsuario).get());
		part.setFechaPart(ts);
		if(part.getNumPart()!=1) {
			part.setNumPart(part.getNumPart()+1);
		}
		part.setNumPart(1);
		
		return repositoryparticipacion.save(part);
	}
	
	@Override
	public Usuarios pagoMercha(String tarjetaCredito,long idUsuario,Double precio) {
		 Usuarios subUser =  usuarioRepository.checkId(idUsuario).get();
		 if(subUser.getDinero()!=0) {
			 subUser.setDinero(subUser.getDinero()+precio);
		 }else {
			 subUser.setDinero(precio);
		 }
		 subUser.setTarjetaCredito(tarjetaCredito);
		 return usuarioRepository.save(subUser);
	}
	
	@Override
	public Comentarios anadirComen(long idUsuario,Long idJugador,String comentario) {
		Comentarios coment = new Comentarios();
		Timestamp ts = Timestamp.from(Instant.now());
		
		coment.setJugador(reposiJugadores.findById(idJugador).get());
		coment.setUsuarios(usuarioRepository.findById(idUsuario).get());
		coment.setComentario(comentario);
		coment.setFechaComent(ts);
		return repositorycomentarios.save(coment);
	}

	@Override
	public void checkSub(Model model,CustomUserDetails userDetails){
		if(usuarioRepository.checkUsuario(userDetails.getUsername()).isPresent()) {
		Usuarios usu = usuarioRepository.checkUsuario(userDetails.getUsername()).get();
		
		if(usu.isSub()) {
			model.addAttribute("EsSub", true);
		}else {
			model.addAttribute("NoSub", true);
		}
	}
	}
	
}
