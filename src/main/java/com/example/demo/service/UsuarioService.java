package com.example.demo.service;

import org.springframework.ui.Model;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.dto.UsuarioUpdate;
import com.example.demo.entity.Comentarios;
import com.example.demo.entity.Participacion;
import com.example.demo.entity.Usuarios;
import com.example.demo.model.CustomUserDetails;

public interface UsuarioService {

	public Usuarios saveUser(UsuarioRegistroDTO resgistroDTO);
	
	public Usuarios updateFile(long id, String foto);
	
	public Usuarios updateUser(UsuarioUpdate usuarioUpdate);
	
	public Usuarios subUser(String tarjetaCredito,long idUsuario);
	
	public Usuarios pagoMercha(String tarjetaCredito,long idUsuario,Double precio);
	
	public Usuarios unsubUser(long idUsuario);
	
	public Participacion unirseEvento(long idUsuario,Long idEvento);
	
	public Comentarios anadirComen(long idUsuario,Long idJugador,String comentario); 

	public void checkSub(Model model,CustomUserDetails userDetails);
	
}
