package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.entity.Usuarios;
import com.example.demo.repository.RepositoryUsuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.FileUploadUtil;

@Controller
@RequestMapping
public class RegistroUsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RepositoryUsuario reposiUsuario;

	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}

	@GetMapping("/")
	public String mostrarMain() {
		return "main";
	}

	@PostMapping("/registro")
	public String registrarseCuentaUsuario(@ModelAttribute(name = "usuariodto") UsuarioRegistroDTO usuariodto,
			Model model, @RequestParam("file") MultipartFile file) {

	/*	if (!(usuariodto.getPassword().equals(usuariodto.getClaveRe()))) {
			model.addAttribute("ErrorClave", true);
			return "registro";
		}

		if (reposiUsuario.checkUsuario(usuariodto.getUsername()).isPresent()) {
			model.addAttribute("ErrorUsuarioNombre", true);
			return "registro";
		}*/

		try {
			Path filepath = Path.of("C:\\Users\\IDB36\\eclipse-workspace\\ThunderVirus2\\src\\main\\resources\\static\\images\\test.png");
		file.transferTo(filepath);
		}catch (Exception e) {
			e.getMessage();
		}
		

		usuarioService.saveUser(usuariodto);

		return "login";
	}

	@GetMapping("/registroland")
	public String mostrarRegistro() {
		return "registro";
	}

	@GetMapping("/perfil")
	public String mostrarPerfil() {
		return "perfil";
	}

	@GetMapping("/evento")
	public String mostrarEvento() {
		return "evento";
	}

	@GetMapping("/Merchandising")
	public String mostrarMerchandising() {
		return "Merchandising";
	}
}
