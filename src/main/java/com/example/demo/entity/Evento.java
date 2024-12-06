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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="evento")
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String descripcion;
	private String nombre;
	@ManyToOne
    @JoinColumn(name = "id_jugador")
	private Jugadores jugador;
	private String premio;
	private Long ganador;
	@Column(name = "foto_evento")
	private String fotoEvento;
	@Column(name = "fecha_ini_evento")
	private Timestamp fechaIniEvento;
	@Column(name = "fecha_fin_evento")
	private Timestamp fechaFinEvento;
	
}
