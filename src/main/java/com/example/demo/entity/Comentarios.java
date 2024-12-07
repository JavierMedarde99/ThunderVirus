package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="comentarios")
@NoArgsConstructor
public class Comentarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_usu")
    private Usuarios usuarios;
	@ManyToOne
    @JoinColumn(name = "id_jugador")
	private Jugadores jugador;
	private String comentario;
	@Column(name = "fecha_coment")
	private Timestamp fechaComent;

	public Comentarios(Usuarios usuarios, Jugadores jugador, String comentario, Timestamp fechaComent){
		this.usuarios = usuarios;
		this.jugador = jugador;
		this.comentario= comentario;
		this.fechaComent = fechaComent;
	}
}
