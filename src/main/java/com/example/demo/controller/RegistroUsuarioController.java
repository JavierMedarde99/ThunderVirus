package com.example.demo.controller;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.example.demo.repository.Repositorycomentarios;
import com.example.demo.repository.RepositoryEvento;
import com.example.demo.repository.RepositoryJugadores;
import com.example.demo.repository.RepositoryMerchandising;
import com.example.demo.repository.RepositoryParticipacion;
import com.example.demo.repository.RepositoryUsuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Log4j2
public class RegistroUsuarioController {

	private final UsuarioService usuarioService;

	private final RepositoryUsuario reposiUsuario;

	private final RepositoryJugadores reposiJugadores;

	private final RepositoryEvento reposiEvento;

	private final RepositoryParticipacion reposiParticipacion;

	private final RepositoryMerchandising repositorymerchandising;

	private final Repositorycomentarios repositorycomentarios;

	@GetMapping("/login")
	public String mostrarLogin() {
		return Constants.LOGIN;
	}

	@GetMapping("/pasarela")
	public String mostrarPasarelaGet() {
		return "pasarela";
	}

	@GetMapping("/")
	public String mostrarMain(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {

		usuarioService.checkSub(model, userDetails);
		return "main";
	}

	@PostMapping("/pasarela")
	public String mostrarPasarela() {
		return "pasarela";
	}

	@PostMapping("/pasarelaMercha")
	public String mostrarPasarelaMercha(Model model, HttpSession sesion, @RequestParam(name = "precio") Double pago) {
		model.addAttribute("dinero", pago);
		sesion.setAttribute("dinero", pago);
		log.info("pago: {}", pago);
		return "pasarelaMercha";
	}

	@GetMapping("/registroland")
	public String mostrarRegistro() {
		return Constants.REGISTRO;
	}

	@GetMapping("/javi")
	public String mostrarJavi(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);

		model.addAttribute("jugadorJavi", reposiJugadores.dataJugadores("javi"));
		String juegos = reposiJugadores.dataJuegos("javi");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosJavi", juego);

		List<Comentarios> listaJaviComent = repositorycomentarios.javiComent();
		List<ComentHTML> listaHTMLComent = new ArrayList();
		for (Comentarios lista : listaJaviComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFechaComent().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(lista.getUsuarios().getUsername());
			listaHTMLComent.add(coment);
		}

		model.addAttribute(Constants.COMENTARIOS, listaHTMLComent);
		return "javi";
	}

	@GetMapping("/antonio")
	public String mostrarAntonio(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);

		model.addAttribute("jugadorAntonio", reposiJugadores.dataJugadores(Constants.ANTONIO));
		String juegos = reposiJugadores.dataJuegos(Constants.ANTONIO);
		String[] juego = juegos.split(",");
		model.addAttribute("juegosAntonio", juego);

		List<Comentarios> listaAntonioComent = repositorycomentarios.antonioComent();
		List<ComentHTML> listaHTMLComent2 = new ArrayList();
		for (Comentarios lista : listaAntonioComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFechaComent().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(lista.getUsuarios().getUsername());
			listaHTMLComent2.add(coment);
		}

		model.addAttribute(Constants.COMENTARIOS, listaHTMLComent2);

		return Constants.ANTONIO;
	}

	@GetMapping("/ivan")
	public String mostrarIvan(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);
		model.addAttribute("jugadorIvan", reposiJugadores.dataJugadores("ivan"));
		String juegos = reposiJugadores.dataJuegos("ivan");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosIvan", juego);

		List<Comentarios> listaIvanComent = repositorycomentarios.ivanComent();
		List<ComentHTML> listaHTMLComent3 = new ArrayList();
		for (Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFechaComent().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(lista.getUsuarios().getUsername());
			listaHTMLComent3.add(coment);
		}

		model.addAttribute(Constants.COMENTARIOS, listaHTMLComent3);

		return "ivan";
	}

	@GetMapping("/adri")
	public String mostrarAdri(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);
		model.addAttribute("jugadorAdri", reposiJugadores.dataJugadores("adri"));
		String juegos = reposiJugadores.dataJuegos("adri");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosAdri", juego);

		List<Comentarios> listaIvanComent = repositorycomentarios.adrianComent();
		List<ComentHTML> listaHTMLComent4 = new ArrayList();
		for (Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFechaComent().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(lista.getUsuarios().getUsername());
			listaHTMLComent4.add(coment);
		}

		model.addAttribute(Constants.COMENTARIOS, listaHTMLComent4);

		return "adri";
	}

	@GetMapping("/angie")
	public String mostrarAngie(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);
		model.addAttribute("jugadorAngie", reposiJugadores.dataJugadores(Constants.ANGIE));
		String juegos = reposiJugadores.dataJuegos(Constants.ANGIE);
		String[] juego = juegos.split(",");
		model.addAttribute("juegosAngie", juego);

		List<Comentarios> listaIvanComent = repositorycomentarios.angieComent();
		List<ComentHTML> listaHTMLComent5 = new ArrayList();
		for (Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFechaComent().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(lista.getUsuarios().getUsername());
			listaHTMLComent5.add(coment);
		}

		model.addAttribute(Constants.COMENTARIOS, listaHTMLComent5);

		return Constants.ANGIE;
	}

	@GetMapping("/tono")
	public String mostrarToño(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);
		model.addAttribute("jugadorTono", reposiJugadores.dataJugadores("tono"));
		String juegos = reposiJugadores.dataJuegos("tono");
		String[] juego = juegos.split(",");
		model.addAttribute("juegosTono", juego);

		List<Comentarios> listaIvanComent = repositorycomentarios.tonoComent();
		List<ComentHTML> listaHTMLComent5 = new ArrayList();
		for (Comentarios lista : listaIvanComent) {
			ComentHTML coment = new ComentHTML();
			coment.setFecha(lista.getFechaComent().toString());
			coment.setComentario(lista.getComentario());
			coment.setNombre(lista.getUsuarios().getUsername());
			listaHTMLComent5.add(coment);
		}

		model.addAttribute(Constants.COMENTARIOS, listaHTMLComent5);

		return "tono";
	}

	@GetMapping("/perfil")
	public String mostrarPerfil(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);

		return "perfil";
	}

	@GetMapping("/evento")
	public String mostrarEvento(@AuthenticationPrincipal CustomUserDetails userDetails, Model model,
			@RequestParam(value = "idevento", required = false) Long idEvento) {
		if (reposiUsuario.checkUsuario(userDetails.getUsername()).isPresent()) {
			Usuarios usu = reposiUsuario.checkUsuario(userDetails.getUsername()).get();

			if (usu.isSub()) {
				model.addAttribute("EsSub", true);
			} else {
				return "redirect:/";
			}
		}

		model.addAttribute("evento", reposiEvento.findAll());
		return "evento";
	}

	@GetMapping("/Merchandising")
	public String mostrarMerchandising(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		usuarioService.checkSub(model, userDetails);
		usuarioService.checkSub(model, userDetails);
		model.addAttribute("tazas", repositorymerchandising.tazas());
		model.addAttribute("camisetas", repositorymerchandising.camisetas());
		return "Merchandising";
	}

	@PostMapping("/registro")
	public String registrarseCuentaUsuario(@ModelAttribute(name = "usuariodto") UsuarioRegistroDTO usuariodto,
			Model model, @RequestParam("file") MultipartFile file) {

		if (!(usuariodto.getPassword().equals(usuariodto.getClaveRe()))) {
			model.addAttribute("ErrorClave", true);
			return Constants.REGISTRO;
		}

		if (reposiUsuario.checkUsuario(usuariodto.getUsername()).isPresent()) {
			model.addAttribute("ErrorUsuarioNombre", true);
			return Constants.REGISTRO;
		}

		log.info("file : {}", usuariodto);
		if (file != null) {
			try {
				Path filepath = Path
						.of("src/main/resources/static/images/perfile/" + usuariodto.getUsername() + ".png");

				file.transferTo(filepath);
			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			try {
				Path filepath = Path.of("src/main/resources/static/images/perfile/avatar.png");

				file.transferTo(filepath);
			} catch (Exception e) {
				e.getMessage();
			}
		}

		usuarioService.saveUser(usuariodto);

		return Constants.LOGIN;
	}

	@PostMapping("/cambiarFoto")
	public String updateFile(@RequestParam("file") MultipartFile file, @RequestParam("idusuario") long idUsuario,
			@RequestParam("username") String userName) {
		String foto = "";
		Random rand = new Random();
		int upperbound = 100;
		int intRandom = rand.nextInt(upperbound);

		try {
			foto = userName + "-" + intRandom + ".png";
			Path filepath = Path.of("src/main/resources/static/images/perfile/" + foto);

			file.transferTo(filepath);
		} catch (Exception e) {
			e.getMessage();
		}

		usuarioService.updateFile(idUsuario, foto);
		return Constants.REDIRECT_PERFIL;
	}

	@PostMapping("/cambio")
	public String updateUser(@ModelAttribute(name = "UsuarioUpdate") UsuarioUpdate Usuarioupdate, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (reposiUsuario.checkUsuario(Usuarioupdate.getUsername()).isPresent()) {
			model.addAttribute("ErrorUsuarioNombre", true);
			return Constants.REDIRECT_PERFIL;
		}

		if (Usuarioupdate.getPassword2() == null && !passwordEncoder.matches(Usuarioupdate.getPassword2(),
				reposiUsuario.checkId(Usuarioupdate.getIdusuario()).get().getPassword())) {
			model.addAttribute("ErrorUsuarioPassworld", true);
			return "perfil";
		}

		usuarioService.updateUser(Usuarioupdate);

		return Constants.REDIRECT_PERFIL;
	}

	@PostMapping("/pago")
	public String subUser(@RequestParam("tarjetaCredito") String tarjetaCredito,
			@RequestParam("idusuario") long idUsuario) {

		usuarioService.subUser(tarjetaCredito, idUsuario);

		return "redirect:/pagoRealizado";
	}

	@GetMapping("/pagoRealizado")
	public String pagoReali() {
		return "pagoRealizado";
	}

	@PostMapping("/pagoMercha")
	public String pagoMercha(@RequestParam("tarjetaCredito") String tarjetaCredito,
			@RequestParam("idusuario") long idUsuario, @RequestParam("precio") Double dinero) {
		log.info("targeta : {}", tarjetaCredito);
		usuarioService.pagoMercha(tarjetaCredito, idUsuario, dinero);

		return "redirect:/pagoRealizado";
	}

	@PostMapping("/unsub")
	public String unsubUser(@RequestParam("idusuario") long idUsuario) {

		usuarioService.unsubUser(idUsuario);

		return "redirect:/";
	}

	@PostMapping("/delUser")
	public String delUser(@RequestParam("idusuario") long idUsuario) {

		reposiUsuario.deleteById(idUsuario);

		return Constants.LOGIN;
	}

	@PostMapping("/unirseEvento")
	public String unirseAlEvento(@RequestParam("idusuario") long idUsuario, @RequestParam("idevento") Long idEvento) {

		if (reposiParticipacion.checkEvent(idUsuario, idEvento).isEmpty()) {
			log.info("entra");
			usuarioService.unirseEvento(idUsuario, idEvento);
			return "redirect:/bienvenido";
		}

		return "redirect:/errorEvento";
	}

	@GetMapping("/bienvenido")
	public String bienvenidoEvento() {
		return "bienvenidoEvento";
	}

	@GetMapping("/errorEvento")
	public String errorEvento() {
		return "errorEvento";
	}

	@PostMapping("/anadirComentarioJavi")
	public String anadirComentarioJav(@RequestParam("id_usu") long idUsuario,
			@RequestParam("id_jugador") Long idJugador, @RequestParam("comentario") String comentario) {

		usuarioService.anadirComen(idUsuario, idJugador, comentario);

		return "redirect:/javi";
	}

	@PostMapping("/anadirComentarioAntonio")
	public String anadirComentarioAnt(@RequestParam("id_usu") long idUsuario,
			@RequestParam("id_jugador") Long idJugador, @RequestParam("comentario") String comentario) {

		usuarioService.anadirComen(idUsuario, idJugador, comentario);

		return "redirect:/antonio";
	}

	@PostMapping("/anadirComentarioIvan")
	public String anadirComentarioIva(@RequestParam("id_usu") long idUsuario,
			@RequestParam("id_jugador") Long idJugador, @RequestParam("comentario") String comentario) {

		usuarioService.anadirComen(idUsuario, idJugador, comentario);

		return "redirect:/ivan";
	}

	@PostMapping("/anadirComentarioAdri")
	public String anadirComentarioAdri(@RequestParam("id_usu") long idUsuario,
			@RequestParam("id_jugador") Long idJugador, @RequestParam("comentario") String comentario) {

		usuarioService.anadirComen(idUsuario, idJugador, comentario);

		return "redirect:/adri";
	}

	@PostMapping("/anadirComentarioAngie")
	public String anadirComentarioAngi(@RequestParam("id_usu") long idUsuario,
			@RequestParam("id_jugador") Long idJugador, @RequestParam("comentario") String comentario) {

		usuarioService.anadirComen(idUsuario, idJugador, comentario);

		return "redirect:/angie";
	}

	@PostMapping("/anadirComentarioTono")
	public String anadirComentarioTono(@RequestParam("id_usu") long idUsuario,
			@RequestParam("id_jugador") Long idJugador, @RequestParam("comentario") String comentario) {

		usuarioService.anadirComen(idUsuario, idJugador, comentario);

		return "redirect:/tono";
	}

}
