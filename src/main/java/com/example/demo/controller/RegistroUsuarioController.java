package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.UsuarioIniciarSesionDTO;
import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.repository.RepositoryUsuario;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping
public class RegistroUsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RepositoryUsuario reposiUsuario;
	
	@GetMapping("/")
	public String mostrarLogin() {
		return "login";
	}
	
	@GetMapping("/main")
	public String mostrarMain() {
		return "main";
	}
	
	@PostMapping("/registro")
	public String registrarseCuentaUsuario(@ModelAttribute(name = "usuariodto") UsuarioRegistroDTO  usuariodto,Model model) {
		
		if(!(usuariodto.getClave().equals(usuariodto.getClaveRe()))) {
				model.addAttribute("ErrorClave", true);
			return "registro";
		}
		
	if(reposiUsuario.checkUsuario(usuariodto.getUsuario()).isPresent()) {
		model.addAttribute("ErrorUsuarioNombre", true);
		return "registro";
	}
		usuarioService.saveUser(usuariodto);
		
		return "main";
	}
	
	@PostMapping("/login")
	public String IniciarUsuario(@ModelAttribute(name = "usuariodto") UsuarioIniciarSesionDTO  usuariodto,Model model) {
		if(reposiUsuario.checkbdUsusario(usuariodto.getUsuario(), usuariodto.getClave()).isEmpty()) {
			model.addAttribute("ErrorUsuario", true);
			return "login";
		}
	return "main";
	}
	
	@GetMapping("/registroland")
	public String mostrarRegistro() {
		return "registro";
	}
	
}
