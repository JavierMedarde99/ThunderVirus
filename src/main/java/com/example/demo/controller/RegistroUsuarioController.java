package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

import com.example.demo.dto.ComentHTML;
import com.example.demo.dto.UsuarioRegistroDTO;
import com.example.demo.dto.UsuarioUpdate;
import com.example.demo.entity.Comentarios;
import com.example.demo.entity.Usuarios;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.repository.RepositoryComentarios;
import com.example.demo.repository.RepositoryEvento;
import com.example.demo.repository.RepositoryJugadores;
import com.example.demo.repository.RepositoryMerchandising;
import com.example.demo.repository.RepositoryParticipacion;
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
	private RepositoryJugadores reposiJugadores;
	
	@Autowired
	private RepositoryEvento reposiEvento;

	@Autowired
	private RepositoryParticipacion reposiParticipacion;
	
	@Autowired
	private RepositoryMerchandising repositorymerchandising;
	
	@Autowired
	private RepositoryComentarios Repositorycomentarios;
	
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
		return "main";
	}

	@PostMapping("/pasarela")
	public String mostrarPasarela() {
		return "pasarela";
	}
	
	@PostMapping("/pasarelaMercha")
	public String mostrarPasarelaMercha(Model model,@RequestParam(name="precio") Double pago) {
		model.addAttribute("dinero", pago);
		log.info("pago: {}",pago);
		return "pasarelaMercha";
	}
	
	@GetMapping("/registroland")
	public String mostrarRegistro() {
		return "registro";
	}

	@GetMapping("/javi")
	public String mostrarJavi(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		
		model.addAttribute("jugadorJavi",reposiJugadores.dataJugadores("javi"));
		String juegos = reposiJugadores.dataJuegos("javi");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosJavi",juego);
		
		List<Comentarios> listaJaviComent = Repositorycomentarios.javiComent();
		List<ComentHTML> listaHTMLComent= new ArrayList();
		for(Comentarios lista : listaJaviComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFecha_coment().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(reposiUsuario.checkId(lista.getId_usu()).get().getUsername());
			listaHTMLComent.add(coment);
		}
				
		model.addAttribute("comentarios",listaHTMLComent);
		return "javi";
	}
	
	@GetMapping("/antonio")
	public String mostrarAntonio(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		
		model.addAttribute("jugadorAntonio",reposiJugadores.dataJugadores("antonio"));
		String juegos = reposiJugadores.dataJuegos("antonio");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosAntonio",juego);
		
		List<Comentarios> listaAntonioComent = Repositorycomentarios.antonioComent();
			List<ComentHTML> listaHTMLComent2= new ArrayList();
		for(Comentarios lista : listaAntonioComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFecha_coment().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(reposiUsuario.checkId(lista.getId_usu()).get().getUsername());
			listaHTMLComent2.add(coment);
		}
				
		model.addAttribute("comentarios",listaHTMLComent2);
		
		
		return "antonio";
	}
	
	@GetMapping("/ivan")
	public String mostrarIvan(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		model.addAttribute("jugadorIvan",reposiJugadores.dataJugadores("ivan"));
		String juegos = reposiJugadores.dataJuegos("ivan");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosIvan",juego);
		
		List<Comentarios> listaIvanComent = Repositorycomentarios.ivanComent();
		List<ComentHTML> listaHTMLComent3= new ArrayList();
		for(Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFecha_coment().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(reposiUsuario.checkId(lista.getId_usu()).get().getUsername());
			listaHTMLComent3.add(coment);
		}
				
		model.addAttribute("comentarios",listaHTMLComent3);
		
		return "ivan";
	}
	
	@GetMapping("/adri")
	public String mostrarAdri(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		model.addAttribute("jugadorAdri",reposiJugadores.dataJugadores("adri"));
		String juegos = reposiJugadores.dataJuegos("adri");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosAdri",juego);
		
		List<Comentarios> listaIvanComent = Repositorycomentarios.adrianComent();
		List<ComentHTML> listaHTMLComent4= new ArrayList();
		for(Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFecha_coment().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(reposiUsuario.checkId(lista.getId_usu()).get().getUsername());
			listaHTMLComent4.add(coment);
		}
				
		model.addAttribute("comentarios",listaHTMLComent4);
		
		return "adri";
	}
	
	@GetMapping("/angie")
	public String mostrarAngie(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		model.addAttribute("jugadorAngie",reposiJugadores.dataJugadores("angie"));
		String juegos = reposiJugadores.dataJuegos("angie");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosAngie",juego);
		
		List<Comentarios> listaIvanComent = Repositorycomentarios.angieComent();
		List<ComentHTML> listaHTMLComent5= new ArrayList();
		for(Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFecha_coment().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(reposiUsuario.checkId(lista.getId_usu()).get().getUsername());
			listaHTMLComent5.add(coment);
		}
				
		model.addAttribute("comentarios",listaHTMLComent5);
		
		return "angie";
	}
	
	@GetMapping("/tono")
	public String mostrarToño(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		model.addAttribute("jugadorTono",reposiJugadores.dataJugadores("tono"));
		String juegos = reposiJugadores.dataJuegos("tono");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosTono",juego);
		
		List<Comentarios> listaIvanComent = Repositorycomentarios.tonoComent();
		List<ComentHTML> listaHTMLComent5= new ArrayList();
		for(Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFecha_coment().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(reposiUsuario.checkId(lista.getId_usu()).get().getUsername());
			listaHTMLComent5.add(coment);
		}
				
		model.addAttribute("comentarios",listaHTMLComent5);
		
		return "tono";
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
	public String mostrarEvento(@AuthenticationPrincipal CustomUserDetails userDetails,Model model,@RequestParam(value = "idevento", required = false) Long idEvento) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			
		
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				return "redirect:/";
			}
		}
		
		
		
		model.addAttribute("evento",reposiEvento.findAll());
		return "evento";
	}

	@GetMapping("/Merchandising")
	public String mostrarMerchandising(@AuthenticationPrincipal CustomUserDetails userDetails,Model model) {
		if(reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();
			
			if(usu.isSub()) {
				model.addAttribute("EsSub", true);
			}else {
				model.addAttribute("NoSub", true);
			}
		}
		model.addAttribute("tazas", repositorymerchandising.tazas());
		model.addAttribute("camisetas", repositorymerchandising.camisetas());
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

		log.info("file : {}",usuariodto);
		if(file!=null) {
			try {
				Path filepath = Path.of("src/main/resources/static/images/perfile/"+usuariodto.getUsername()+".png");
				
			file.transferTo(filepath);
			}catch (Exception e) {
				e.getMessage();
			}
		}else {
			try {
				Path filepath = Path.of("src/main/resources/static/images/perfile/avatar.png");
				
			file.transferTo(filepath);
			}catch (Exception e) {
				e.getMessage();
			}
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
			 Path filepath = Path.of("src/main/resources/static/images/perfile/"+foto);
			
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
			return "redirect:/perfil";
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
	
	@PostMapping("/pagoMercha")
	public String pagoMercha(@RequestParam("tarjetaCredito") int tarjetaCredito,@RequestParam("idusuario") long idUsuario,@RequestParam("precio") Double dinero) {
		log.info("dinero: {}", dinero);
		Integer tarjetaCreditoInt = Integer.valueOf(tarjetaCredito);
		 usuarioService.pagoMercha(tarjetaCreditoInt, idUsuario,dinero);
		
		return "redirect:/Merchandising";
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
	
	@PostMapping("/unirseEvento")
	public String unirseAlEvento(@RequestParam("idusuario") long idUsuario,@RequestParam("idevento") Long idEvento) {
		
		if(reposiParticipacion.checkEvent(idUsuario, idEvento).isEmpty()) {
			usuarioService.unirseEvento(idUsuario, idEvento);
		}
		
		
		return "redirect:/evento";
	}
	
	@PostMapping("/anadirComentarioJavi")
	public String anadirComentarioJav(@RequestParam("id_usu") long idUsuario,@RequestParam("id_jugador") Long idJugador,@RequestParam("comentario") String comentario) {
		
		usuarioService.anadirComen(idUsuario, idJugador, comentario);
		
		
		return "redirect:/javi";
	}
	
	@PostMapping("/anadirComentarioAntonio")
	public String anadirComentarioAnt(@RequestParam("id_usu") long idUsuario,@RequestParam("id_jugador") Long idJugador,@RequestParam("comentario") String comentario) {
		
		usuarioService.anadirComen(idUsuario, idJugador, comentario);
		
		
		return "redirect:/antonio";
	}
	
	@PostMapping("/anadirComentarioIvan")
	public String anadirComentarioIva(@RequestParam("id_usu") long idUsuario,@RequestParam("id_jugador") Long idJugador,@RequestParam("comentario") String comentario) {
		
		usuarioService.anadirComen(idUsuario, idJugador, comentario);
		
		
		return "redirect:/ivan";
	}
	
	@PostMapping("/anadirComentarioAdri")
	public String anadirComentarioAdri(@RequestParam("id_usu") long idUsuario,@RequestParam("id_jugador") Long idJugador,@RequestParam("comentario") String comentario) {
		
		usuarioService.anadirComen(idUsuario, idJugador, comentario);
		
		
		return "redirect:/adri";
	}
	
	@PostMapping("/anadirComentarioAngie")
	public String anadirComentarioAngi(@RequestParam("id_usu") long idUsuario,@RequestParam("id_jugador") Long idJugador,@RequestParam("comentario") String comentario) {
		
		usuarioService.anadirComen(idUsuario, idJugador, comentario);
		
		
		return "redirect:/angie";
	}
	
	@PostMapping("/anadirComentarioTono")
	public String anadirComentarioTono(@RequestParam("id_usu") long idUsuario,@RequestParam("id_jugador") Long idJugador,@RequestParam("comentario") String comentario) {
		
		usuarioService.anadirComen(idUsuario, idJugador, comentario);
		
		
		return "redirect:/tono";
	}
	
}
