package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.demo.dto.UsuarioUpdate;
import com.example.demo.entity.Usuarios;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.repository.RepositoryMerchandising;
import com.example.demo.repository.RepositoryUsuario;
import com.example.demo.service.UsuarioService;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping
@Log4j2
public class RegistroUsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RepositoryUsuario reposiUsuario;

	@Autowired
	private RepositoryMerchandising repositorymerchandising;
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}

	@GetMapping("/")
	public String mostrarMain(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		log.info("entra");
		return "main";
	}

	@PostMapping("/pasarela")
	public String mostrarPasarela() {
		return "pasarela";
	}
	
	@GetMapping("/registroland")
	public String mostrarRegistro() {
		return "registro";
	}

	@GetMapping("/perfil")
	public String mostrarPerfil(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		
		return "perfil";
	}

	@GetMapping("/evento")
	public String mostrarEvento() {
		return "evento";
	}

	@GetMapping("/Merchandising")
	public String mostrarMerchandising(Model model) {
		model.addAttribute("merchandising", repositorymerchandising.findAll());
		return "Merchandising";
	}
	
	@PostMapping("/registro")
	public String registrarseCuentaUsuario(@ModelAttribute(name = "usuariodto") UsuarioRegistroDTO usuariodto,
			Model model, @RequestParam("file") MultipartFile file) {

		if (!(usuariodto.getPassword().equals(usuariodto.getClaveRe()))) {
			model.addAttribute("ErrorClave", true);
			return "registro";
		}

		if (reposiUsuario.checkUsuario(usuariodto.getUsername()).isPresent()) {
			model.addAttribute("ErrorUsuarioNombre", true);
			return "registro";
		}

		try {
			
			
			//Path filepath = Path.of(getClass().getResource("/static/images/perfile").getPath()); 
			Path filepath = Path.of("C:\\Users\\javie\\eclipse-workspace\\ThunderVirus\\src\\main\\resources\\static\\images\\perfile\\"+usuariodto.getUsername()+".png");
			//Path filepath = Path.of("/resources/static/images/perfile/"+usuariodto.getUsername()+".png");
			
		file.transferTo(filepath);
		}catch (Exception e) {
			e.getMessage();
		}
		
		
		usuarioService.saveUser(usuariodto);

		return "login";
	}
	
	@PostMapping("/cambiarFoto")
	public String updateFile(@RequestParam("file") MultipartFile file,@RequestParam("idusuario") long idUsuario,@RequestParam("username") String userName) {
		String foto="";
		Random rand = new Random(); 
	      int upperbound = 100;
	      int int_random = rand.nextInt(upperbound); 
	      
		try {
			 foto = userName+"-"+int_random+".png";
			
			 //Path filepath = Path.of(getClass().getResource("/static/images/perfile/"+foto).getPath()); 
			Path filepath = Path.of("C:\\Users\\javie\\eclipse-workspace\\ThunderVirus\\src\\main\\resources\\static\\images\\perfile\\"+foto);
			//Path filepath = Path.of("/resources/static/images/perfile/"+usuariodto.getUsername()+".png");
		file.transferTo(filepath);
		}catch (Exception e) {
			e.getMessage();
		}
		
		usuarioService.updateFile(idUsuario, foto);
		return "redirect:/perfil";
	}
	
	@PostMapping("/cambio")
	public String updateUser(@ModelAttribute(name = "UsuarioUpdate") UsuarioUpdate Usuarioupdate,Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if (reposiUsuario.checkUsuario(Usuarioupdate.getUsername()).isPresent()) {
			model.addAttribute("ErrorUsuarioNombre", true);
			return "perfil";
		}
		
		
	
			if(Usuarioupdate.getPassword2() == null && !passwordEncoder.matches(Usuarioupdate.getPassword2(), reposiUsuario.checkId(Usuarioupdate.getIdusuario()).get().getPassword())) {
			model.addAttribute("ErrorUsuarioPassworld", true);
			return "perfil";
		}
		
		usuarioService.updateUser(Usuarioupdate);
		
		
		return "redirect:/perfil";
	}
	
	@PostMapping("/pago")
	public String subUser(@RequestParam("tarjetaCredito") int tarjetaCredito,@RequestParam("idusuario") long idUsuario) {
		
		Integer tarjetaCreditoInt = Integer.valueOf(tarjetaCredito);
		 usuarioService.subUser(tarjetaCreditoInt, idUsuario);
		
		return "redirect:/";
	}
	
	@PostMapping("/unsub")
	public String unsubUser(@RequestParam("idusuario") long idUsuario) {
		
		
		 usuarioService.unsubUser(idUsuario);
		
		return "redirect:/";
	}
	
	@PostMapping("/delUser")
	public String delUser(@RequestParam("idusuario") long idUsuario) {
		
		
		reposiUsuario.deleteById(idUsuario);
		
		return "login";
	}
	
}
