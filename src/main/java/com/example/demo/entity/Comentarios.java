package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="comentarios")
@AllArgsConstructor
@NoArgsConstructor
public class Comentarios {

	@Id
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "id_usu")
    private Usuarios usuarios;
	@ManyToOne
    @JoinColumn(name = "id_jugador")
	private Jugadores jugador;
	private String comentario;
	@Column(name = "fecha_coment")
	private Timestamp fechaComent;
}
