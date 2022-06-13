package com.example.demo.service;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.dto.UsuarioUpdate;
import com.example.demo.entity.Comentarios;
import com.example.demo.entity.Participacion;
import com.example.demo.entity.Usuarios;
import com.example.demo.repository.RepositoryComentarios;
import com.example.demo.repository.RepositoryParticipacion;
import com.example.demo.repository.RepositoryUsuario;

@Service
public class UsuarioServicioImpl implements UsuarioService{
	
	final Double tarifa=5.0;
	
	@Autowired
	private RepositoryUsuario usuarioRepository;
	
	@Autowired
	private RepositoryParticipacion Repositoryparticipacion;
	
	@Autowired
	private RepositoryComentarios Repositorycomentarios;
	
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
		 if(subUser.getDinero()!=0) {
			 subUser.setDinero(subUser.getDinero()+tarifa);
		 }else {
			 subUser.setDinero(tarifa);
		 }
		 
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
	
	@Override
	public Participacion unirseEvento(long idUsuario,Long idEvento) {
		Timestamp ts = Timestamp.from(Instant.now());
		Participacion part = new Participacion();
		part.setId_evento(idEvento);
		part.setId_usu(idUsuario);
		part.setFecha_part(ts);
		if(part.getNum_part()!=1) {
			part.setNum_part(part.getNum_part()+1);
		}
		part.setNum_part(1);
		
		return Repositoryparticipacion.save(part);
	}
	
	@Override
	public Usuarios pagoMercha(Integer tarjetaCredito,long idUsuario,Double precio) {
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
		coment.setId_jugador(idJugador);
		coment.setId_usu(idUsuario);
		coment.setComentario(comentario);
		coment.setFecha_coment(ts);
		return Repositorycomentarios.save(coment);
	}
}
