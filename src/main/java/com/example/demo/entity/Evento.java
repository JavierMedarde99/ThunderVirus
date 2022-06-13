package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name="evento")
@AllArgsConstructor
public class Evento {

	public Evento(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String descripcion;
	private String nombre;
	private long id_jugador;
	private String premio;
	private Long ganador;
	private String foto_evento;
	private Timestamp fecha_ini_evento;
	private Timestamp fecha_fin_evento;
	
}
