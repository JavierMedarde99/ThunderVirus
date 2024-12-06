package com.example.demo.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="jugadores")
@AllArgsConstructor
@NoArgsConstructor
public class Jugadores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombre;
	@Column(name = "nombre_jugador")
	private String nombreJugador;
	@Column(name = "fecha_nac")
	private String fechaNac;
	private String juegos;
	private String foto;
}
